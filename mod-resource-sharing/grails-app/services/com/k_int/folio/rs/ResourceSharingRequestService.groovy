package com.k_int.folio.rs

import static grails.async.Promises.*
import static grails.gorm.multitenancy.Tenants.*

import org.springframework.beans.factory.annotation.Value

import grails.async.Promise
import grails.events.EventPublisher
import grails.events.bus.EventBusAware
import grails.gorm.multitenancy.CurrentTenant
import grails.gorm.multitenancy.WithoutTenant

@CurrentTenant
class ResourceSharingRequestService implements EventPublisher {
  
  Random random = new Random()
  
  @Value('${rs.rota.depth}')
  int depth
  
  private State getState(String modelCode, String stateCode) {
    
    // The model.
    StateModel model = StateModel.findOrCreateByCode(modelCode)
    model.save(failOnError:true)
    
    State s = State.findOrCreateByOwnerAndCode(model, stateCode)
    s.save(failOnError: true)
  }
  
  private ResourceSharingRequest createRota (ResourceSharingRequest request) {
    
    State s = getState('Generic Script', 'IDOL')
    
    for (int index : (1..depth)) {
      request.addToRota(
        role          : 'REQUESTER',
        rotaSequence  : index,
        service       : ResourceSharingService.findOrCreateBySymbol("Institution ${index}"),
        currentState  : s
      )
    }
    
    request
  }
  
  @WithoutTenant
  protected void setRequestState(final String theTenantId, final String prid, final String stateCode) {
    
    def me = this
    withId (theTenantId) {
      ProtocolRequest pr = ProtocolRequest.get(prid)
      pr.currentState = getState('Generic Script', stateCode)
      pr.save(failOnError:true, flush:true)
      
      // Raise an event
      me.notify("rota${stateCode.toLowerCase()}".replaceAll('\\s', ''), pr.owner.id, prid)
    }
  }
  
  public Promise startRota(final String theTenantId, final String rsrId) {
    // We attempt to do this asynchronously.
    Promise p = task {
      
      // Because this is an asynchronous task and will be executed outside of the current request
      // context we need to use the withId id method to use a particular tenant id.
      
      withId (theTenantId) {
        // This closure is run as a background task.
        notify("rotastart", rsrId)
        
        // Read in the object.
        ResourceSharingRequest rsr = ResourceSharingRequest.read(rsrId)
        
        if (!rsr.currentServiceRequest) {
          
          // Start the service.
          def theRota = rsr.rota
          int terminator = random.nextInt(theRota.size()+1)
          
          String finalState
          String finalEventName
          if (terminator == theRota.size()) {
            // Item NOT SUPPLIED
            finalState = 'NOT SUPPLIED'
            terminator--
          } else {
            // Item SHIPPED by rota item at index identified by terminator.
            finalState = 'SHIPPED'
          }
          
          for (int i=0; i<=terminator; i++) {
            
            def prId = theRota[i].id
            ResourceSharingRequest.withNewSession {
              // Set the request type...
              setRequestState(theTenantId, prId, 'PENDING')
              
              // Read in the altered request.
              ProtocolRequest pr = ProtocolRequest.get(prId)
              
              // Set current pointer.
              pr.owner.currentServiceRequest = pr
              
              // Save the owner.
              pr.owner.save( failOnError: true, flush:true )
              pr.save( failOnError: true, flush:true )
            }
            
            // Sleep between 2 and 10 seconds.
            int pause = random.nextInt( 3 ) + 8
            println "Wait for ${pause} seconds (simulating response time)"
            sleep(pause * 1000)
            
            ResourceSharingRequest.withNewSession {
              if (i == terminator) {
                // End here.
                setRequestState(theTenantId, prId, finalState)
              } else {
                // Just set to NOT SUPPLIED.
                setRequestState(theTenantId, prId, 'NOT SUPPLIED')
              }
            }
          }
        }
        
        // End
        notify("rotaend", rsrId)
        true
      }
    }
    
    // Error.
    p.onError { Throwable err ->
      log.error "Error processing rota for ${rsrId}: ${err.message}", err
    }
    
    p.onComplete { result ->
      log.info "Rota for ${rsrId} completed."
    }
    
    // This method will not block. We pass the promise back to the caller.
    p
  }
  
  ResourceSharingRequest initializeRequest() {
    initializeRequest(new ResourceSharingRequest())
  }
  
  ResourceSharingRequest initializeRequest (ResourceSharingRequest request) {
    // Mutate and return the request with the defaults set.
    createRota(request)
  }
}
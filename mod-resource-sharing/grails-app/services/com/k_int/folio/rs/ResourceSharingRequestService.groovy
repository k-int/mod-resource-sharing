package com.k_int.folio.rs

import org.springframework.beans.factory.annotation.Value

import grails.gorm.multitenancy.CurrentTenant

@CurrentTenant
class ResourceSharingRequestService {
  
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
  
  ResourceSharingRequest initializeRequest() {
    initializeRequest(new ResourceSharingRequest())
  }
  
  ResourceSharingRequest initializeRequest (ResourceSharingRequest request) {
    // Mutate and return the request with the defaults set.
    createRota(request)
  }
}
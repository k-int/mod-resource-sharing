package com.k_int.folio.rs


import grails.gorm.multitenancy.CurrentTenant
import grails.plugin.springsecurity.annotation.Secured
import okapi.OkapiTenantAwareController
import static grails.gorm.multitenancy.Tenants.*

@CurrentTenant
class RequestController extends OkapiTenantAwareController<ResourceSharingRequest> {
  
  ResourceSharingRequestService resourceSharingRequestService
  
  RequestController() {
    super(ResourceSharingRequest)
  }
  
  @Override
  protected ResourceSharingRequest createResource() {

    log.debug("RequestController::createResource(${params})");
    log.debug("json:${request.JSON}");

    Map defaults = [:]
    if (patron) {
      defaults.patronId = patron.id
    }
    
    // Create with defaults.
    ResourceSharingRequest instance = createResource(defaults)
    
    // Add the initial values for the Rota etc..
    resourceSharingRequestService.initializeRequest(instance)
    
    // Bind the supplied data
    bindData instance, getObjectToBind()
    
    // Return our new instance.
    instance
  }
  
  @Override
  def index() {
    
    List<String> filters = [] + params.list("filters")
    
    if (patron && !hasAuthority('folio.resource-sharing.any.read')) {
      // We force an extra filter in here.
      filters << "patronId==${patron.id}"
    }
    
    def results = simpleLookupService.lookup(this.resource, params.term, Math.min(params.int('perPage') ?: 100, 100), params.int("page"), filters, params.list("match"))
    respond results
  }
  
  @Secured('hasAuthority("folio.resource-sharing.admin")')
  def rotaStart() {
    
    if (params.requestId) {
      resourceSharingRequestService.startRota(currentId(), params.requestId)
      render status: 204
    } else {
      render status: 404
    }
    return
  }
}

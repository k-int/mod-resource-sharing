package com.k_int.folio.rs

import grails.gorm.multitenancy.CurrentTenant
import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import okapi.OkapiTenantAwareController

@CurrentTenant
class RequestController extends OkapiTenantAwareController<ResourceSharingRequest> {
  
  RequestController() {
    super(ResourceSharingRequest)
  }
  
  @Override
  protected ResourceSharingRequest createResource() {
    Map defaults = [:]
    if (patron) {
      defaults.patronId = patron.id
    }
    ResourceSharingRequest instance = createResource(defaults)
    bindData instance, getObjectToBind()
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
}

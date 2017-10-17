package com.k_int.folio.rs


import com.k_int.web.toolkit.rest.TenantAwareRestfulController
import grails.converters.*
import grails.gorm.multitenancy.*


@CurrentTenant
class RequestController extends TenantAwareRestfulController<ResourceSharingRequest> {

  static responseFormats = ['json', 'xml']

  RequestController() {
    super(ResourceSharingRequest)
  }

  /**
   * Make a new request for a specific user / patron
   */
  def save() {
    log.debug("RequestController::save ${params} ${request.JSON}");
    super.save();
  }

  def getObjectToBind() {
    return request.JSON
  }

  def search(String q, Integer max ) { 
    if (q) {
        def query = ResourceSharingRequest.where { 
            title ==~ "%${q}%"
        }
        respond query.list(max: Math.min( max ?: 10, 100)) 
    }
    else {
        respond([]) 
    }
  }
	
}

package com.k_int.folio.rs


import grails.rest.*
import grails.converters.*
import grails.gorm.multitenancy.CurrentTenant

class RequestController extends RestfulController {

  static responseFormats = ['json', 'xml']

  RequestController() {
    super(ResourceSharingRequest)
  }

  /**
   * Make a new request for a specific user / patron
   */
  @CurrentTenant
  def save() {
    log.debug("RequestController::save ${params} ${request.JSON}");
    super.save();
  }

  def getObjectToBind() {
    return request.JSON
  }

  @CurrentTenant
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

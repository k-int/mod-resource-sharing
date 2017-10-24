package com.k_int.folio.rs


import grails.rest.*

import com.k_int.web.toolkit.rest.TenantAwareRestfulController

import grails.converters.*
import grails.gorm.multitenancy.CurrentTenant

@CurrentTenant
class PartyController extends TenantAwareRestfulController<Party>  {

  static responseFormats = ['json', 'xml']

  PartyController() {
    super(Party)
  }

  def save() {
    log.debug("PartyController::save ${params} ${request.JSON}");
    super.save();
  }

  def getObjectToBind() {
    return request.JSON
  }

  def search(String q, Integer max ) { 
    if (q) {
        def query = Party.where { 
            name ==~ "%${q}%"
        }
        respond query.list(max: Math.min( max ?: 10, 100)) 
    }
    else {
        respond([]) 
    }
  }
	
}

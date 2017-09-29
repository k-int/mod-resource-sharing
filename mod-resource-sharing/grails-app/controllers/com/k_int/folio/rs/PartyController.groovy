package com.k_int.folio.rs


import grails.rest.*
import grails.converters.*
import grails.gorm.multitenancy.CurrentTenant

class PartyController extends RestfulController {

	static responseFormats = ['json', 'xml']

  PartyController() {
    super(Party)
  }

  @CurrentTenant
  def save() {
    log.debug("PartyController::save ${params} ${request.JSON}");
    super.save();
  }

  @CurrentTenant
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

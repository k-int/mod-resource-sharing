package com.k_int.folio.rs


import com.k_int.web.toolkit.rest.TenantAwareRestfulController
import com.k_int.web.tools.SimpleLookupService
import grails.converters.*
import grails.core.GrailsApplication
import grails.gorm.multitenancy.*
import org.grails.datastore.gorm.GormEnhancer
import org.grails.datastore.mapping.core.Datastore
import org.grails.orm.hibernate.HibernateDatastore
import org.hibernate.SessionFactory

import static grails.gorm.multitenancy.Tenants.*

@CurrentTenant
class RequestController extends TenantAwareRestfulController<ResourceSharingRequest> {

  static responseFormats = ['json', 'xml']
  SimpleLookupService simpleLookupService

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

//  def search(String q, Integer max ) { 
//    if (q) {
//        def query = ResourceSharingRequest.where { 
//            title ==~ "%${q}%"
//        }
//        respond query.list(max: Math.min( max ?: 10, 100)) 
//    }
//    else {
//        respond([]) 
//    }
//  }
  

  def search (String term) {
//    def ctrl = this
//    SessionFactory sessionFactory = grailsApplication.getMainContext().getBean('sessionFactory')
//    HibernateDatastore datastore  = grailsApplication.getMainContext().getBean('datastore')
//    
//    datastore.enableMultiTenancyFilter()
//    // do work with the session factory
//    sessionFactory.openSession()
//
//    
//      simpleLookupService.metaClass.session = session
//      ctrl.metaClass.session = session
//      
//      def results = simpleLookupService.lookup(this.resource, term, Math.min(params.int('perPage') ?: 100, 100), params.int("page"), params.list("filters"), params.list("match"))
//      
//      respond results
      
    
      
//    withCurrent { def tenantId, def session ->
//      Datastore datastore = GormEnhancer.findSingleDatastore()
      def results = simpleLookupService.lookup(this.resource, term, Math.min(params.int('perPage') ?: 100, 100), params.int("page"), params.list("filters"), params.list("match"))
      respond results
//    }
  }
	
}

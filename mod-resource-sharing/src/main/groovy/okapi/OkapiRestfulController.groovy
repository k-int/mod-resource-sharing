package okapi

import com.k_int.web.tools.SimpleLookupService

import grails.artefact.Artefact
import grails.rest.RestfulController

@Artefact('Controller')
class OkapiRestfulController<T> extends RestfulController<T> {

  static responseFormats = ['json', 'xml']
  SimpleLookupService simpleLookupService

  OkapiRestfulController(Class<T> resource) {
    super(resource)
  }
  
  OkapiRestfulController(Class<T> resource, boolean readOnly) {
    super(resource, readOnly)
  }

  def getObjectToBind() {
    return request.JSON
  }

  def search (String term) {
    def results = simpleLookupService.lookup(this.resource, term, Math.min(params.int('perPage') ?: 100, 100), params.int("page"), params.list("filters"), params.list("match"))
    respond results
  }
}

package okapi

import com.k_int.folio.rs.ResourceSharingRequest
import com.k_int.web.toolkit.rest.TenantAwareRestfulController
import com.k_int.web.tools.SimpleLookupService
import grails.artefact.Artefact
import grails.gorm.multitenancy.CurrentTenant

@CurrentTenant
@Artefact('Controller')
class OkapiTenantAwareController<T> extends TenantAwareRestfulController<T> {

  static responseFormats = ['json', 'xml']
  SimpleLookupService simpleLookupService

  OkapiTenantAwareController(Class<T> resource) {
    super(resource)
  }
  
  OkapiTenantAwareController(Class<T> resource, boolean readOnly) {
    super(resource, readOnly)
  }

  def getObjectToBind() {
    return request.JSON
  }

  def search () {
    String term = params.term
    def match = params.list("match")
    def results = simpleLookupService.lookup(this.resource, term, Math.min(params.int('perPage') ?: 100, 100), params.int("page"), params.list("filters"), match)
    respond results
  }
}

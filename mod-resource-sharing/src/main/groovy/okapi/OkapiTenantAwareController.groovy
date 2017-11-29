package okapi

import com.k_int.folio.rs.ResourceSharingRequest
import com.k_int.web.toolkit.rest.TenantAwareRestfulController
import com.k_int.web.tools.SimpleLookupService
import grails.artefact.Artefact
import grails.gorm.multitenancy.CurrentTenant
import grails.plugin.springsecurity.SpringSecurityService

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.access.AccessDeniedHandlerImpl

@CurrentTenant
@Artefact('Controller')
class OkapiTenantAwareController<T> extends TenantAwareRestfulController<T> {

  static responseFormats = ['json', 'xml']
  
  SimpleLookupService simpleLookupService
  SpringSecurityService springSecurityService

  OkapiTenantAwareController(Class<T> resource) {
    super(resource)
  }
  
  OkapiTenantAwareController(Class<T> resource, boolean readOnly) {
    super(resource, readOnly)
  }

  def getObjectToBind() {
    request.JSON
  }
  
  protected UserDetails getPatron() {
    springSecurityService.principal
  }
  
  protected boolean hasAnyAuthority(Set auths) {
    AccessDeniedHandlerImpl f
    def pAuths = patron?.authorities?.collect { it.authority }
    def intersect = pAuths?.intersect(auths)
    intersect
  }
  
  protected boolean hasAuthority(String auth) {
    def intersect = hasAnyAuthority((auth ? [auth] : []) as Set)
    intersect
  }
  
  def index() {
    respond simpleLookupService.lookup(this.resource, params.term, Math.min(params.int('perPage') ?: 100, 100), params.int("page"), params.list("filters"), params.list("match"))
  }
}

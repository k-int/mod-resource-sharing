package com.k_int.folio.rs

import grails.gorm.multitenancy.CurrentTenant
import okapi.OkapiTenantAwareController

import com.k_int.folio.rs.ResourceSharingService;

/**
 * A resource sharing service represents a protocol endpoint. Examples could be email:some.service@some.email.host or tcp:some.host.name:1234
 */
@CurrentTenant
class ResourceSharingServiceController extends OkapiTenantAwareController<ResourceSharingService>  {

  ResourceSharingServiceController() {
    super(ResourceSharingService)
  }	
}

package com.k_int.folio.rs

import grails.gorm.multitenancy.CurrentTenant
import okapi.OkapiTenantAwareController

@CurrentTenant
class RequestController extends OkapiTenantAwareController<ResourceSharingRequest> {
  RequestController() {
    super(ResourceSharingRequest)
  }
}

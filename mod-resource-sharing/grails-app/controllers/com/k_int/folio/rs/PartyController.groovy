package com.k_int.folio.rs

import grails.gorm.multitenancy.CurrentTenant
import okapi.OkapiTenantAwareController

@CurrentTenant
class PartyController extends OkapiTenantAwareController<Party>  {

  PartyController() {
    super(Party)
  }	
}

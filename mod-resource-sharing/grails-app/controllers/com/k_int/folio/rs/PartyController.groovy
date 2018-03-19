package com.k_int.folio.rs

import com.k_int.okapi.OkapiTenantAwareController

import grails.gorm.multitenancy.CurrentTenant

@CurrentTenant
class PartyController extends OkapiTenantAwareController<Party>  {

  PartyController() {
    super(Party)
  }	
}

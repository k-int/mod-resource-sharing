package com.k_int.folio.rs

import grails.gorm.multitenancy.CurrentTenant
import okapi.OkapiTenantAwareController
import grails.converters.*


@CurrentTenant
class AdminController {

  def RSProfileService

  def registerSymbol(String symbol) {

    def result = [ status: 'OK' ];
    String tenantId = request.getHeader('X-Okapi-Tenant')?.toLowerCase()


    // @CurrentTenant will provide tenantId. The RSProfileService is also tenant aware and accesses tenantId via that path
    log.debug("AdminController::registerSymbol(${symbol}) -- ${tenantId}");
    RSProfileService.registerSymbol(symbol, tenantId);

    render result as JSON
  }
}

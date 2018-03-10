package com.k_int.folio.rs

import grails.gorm.multitenancy.CurrentTenant
import grails.gorm.multitenancy.WithoutTenant

import okapi.OkapiTenantAwareController
import grails.converters.*


@CurrentTenant
class AdminController {

  def RSProfileService

  @WithoutTenant
  def registerSymbol(String symbol) {

    def result = [ status: 'OK' ];
    String tenantId = request.getHeader('X-Okapi-Tenant')?.toLowerCase()


    // @CurrentTenant will provide tenantId. The RSProfileService is also tenant aware and accesses tenantId via that path
    log.debug("AdminController::registerSymbol(${symbol}) -- ${tenantId}");
    RSProfileService.registerSymbol(symbol, tenantId);

    TenantSymbolMapping.list().each { t ->
      log.debug("Existing tenant mapping: ${t}");
    }

    render result as JSON
  }
}

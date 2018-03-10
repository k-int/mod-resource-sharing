package com.k_int.folio.rs

import grails.gorm.transactions.Transactional

import grails.gorm.multitenancy.CurrentTenant
import grails.gorm.multitenancy.WithoutTenant


/**
 * RSProfileService provides access to global config for the tenant and controls the bridge between
 * underlying global ILL services and this tenants symbols.
 *
 *
 */
@CurrentTenant
@Transactional
class RSProfileService {

  /* Register the pairing of the symbol with a particular tenantId. This will cause notifications for the given synbol
   * to be notified to the appropriate tenant 
   * @param symbol
   * @param tenantId - Added to service via @Transactional annotation
   */
  public boolean registerSymbol(String symbol, String tenant_id) {

    log.debug("RSProfileService::registerSymbol(${symbol}) - ${tenant_id}");

    TenantSymbolMapping.findAllBySymbol(symbol).each { m ->
     log.debug("Remove old mapping ${m}");
      m.delete()
    }

    /* N.B. TenantSymbolMapping is not a multi-tenant domain class - the table lives at the module level */
    TenantSymbolMapping tsm = new TenantSymbolMapping(tenantId:tenant_id,symbol:symbol).save(flush:true, failOnError:true);
    return true;
  }

  public Map getDefaultRequesterPolicy() {
    return [
      defaultRequesterSymbol:'none',
      defaultRota:[]
    ]
  }
  
}

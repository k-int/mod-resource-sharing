package com.k_int.folio.rs

/**
 *  N.B. This table is not per-tenant - it lives at the module level and acts to allow incoming
 *  protocol messages to know which tenant should handle the message.
 */
class TenantSymbolMapping {
  
  String id
  String tenantId
  String symbol

  static constraints = {
    tenantId(nullable:false, blank:false)
    symbol(nullable:false, blank:false)
  }

  static mapping = {
    table 'rs_tenant_mapping'
    id(column:'rstm_id', generator: 'uuid', length:36)

    tenantId column:'rstm_tenant_id'
      symbol column:'rstm_symbol'
  }

}

package com.k_int.folio.rs
import grails.gorm.MultiTenant


class SymbolService implements MultiTenant<SymbolService> {

  String id
  ResourceSharingSymbol symbol
  ResourceSharingService service
  String priority

  static constraints = {
    symbol(nullable:false, blank:false)
    service(nullable:false, blank:false)
    priority(nullable:true, blank:false)
  }

  static mapping = {
    table 'rs_symbol_service'
    id(column:'rsss_id', generator: 'uuid', length:36)
    symbol column:'rsss_symbol_fk'
    service column:'rsss_service_fk'
    priority column:'rsss_priority'
  }

}

package com.k_int.folio.rs
import grails.gorm.MultiTenant


class ResourceSharingSymbol implements MultiTenant<ResourceSharingSymbol> {

  Party owner
  String id
  String symbol

  static constraints = {
    symbol(nullable:true, blank:false)
    owner(nullable:false, blank:false)
  }

  static mapping = {
    table 'rs_symbol'
    id(column:'rss_id', generator: 'uuid', length:36)
    symbol column:'rss_symbol'
    owner column:'rss_owner_party_fk'
  }

}

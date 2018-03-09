package com.k_int.folio.rs
import grails.gorm.MultiTenant


class ResourceSharingSymbol implements MultiTenant<ResourceSharingSymbol> {

  Party owner
  NamingAuthority authority
  String id
  String symbol

  static constraints = {
    symbol(nullable:true, blank:false)
    authority(nullable:false, blank:false)
    owner(nullable:false, blank:false)
  }

  static mapping = {
    table 'rs_symbol'
    id(column:'rss_id', generator: 'uuid', length:36)
    authority column:'rss_authority_fk'
    symbol column:'rss_symbol'
    owner column:'rss_owner_party_fk'
  }

}

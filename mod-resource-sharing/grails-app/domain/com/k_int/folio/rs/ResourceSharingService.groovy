package com.k_int.folio.rs
import grails.gorm.MultiTenant


class ResourceSharingService implements MultiTenant<ResourceSharingService> {

  String id
  String symbol

  static constraints = {
    symbol(nullable:true, blank:false)
  }

  static mapping = {
    table 'rs_service'
    id(column:'rss_id', generator: 'uuid', length:36)
    symbol column:'rss_symbol'
  }

}

package com.k_int.folio.rs
import grails.gorm.MultiTenant


class NamingAuthority implements MultiTenant<NamingAuthority> {

  String id
  String shortcode
  String name

  static constraints = {
    shortcode(nullable:false, blank:false)
    name(nullable:false, blank:false)
  }

  static mapping = {
    table 'rs_naming_authority'
    id(column:'rsna_id', generator: 'uuid', length:36)
    authority column:'rsna_shortcode'
    symbol column:'rsna_name'
  }

}

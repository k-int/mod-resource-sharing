package com.k_int.folio.rs

import grails.gorm.MultiTenant;

class Protocol implements MultiTenant<Protocol> {

  String id
  String code
  String description

  static constraints = {
    code(nullable:false, blank:false);
    description(nullable:true, blank:false);
  }

  static mapping = {
    table 'rs_protocol'
    id(column:'prot_id', generator: 'uuid', length:36)
    code column:'prot_code'
    description column:'prot_description'
  }

}

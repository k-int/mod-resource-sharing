package com.k_int.folio.rs

import grails.gorm.MultiTenant;

class ProtocolAction  implements MultiTenant<ProtocolAction> {

  String id
  String code
  String description

  static belongsTo = [
    protocol:Protocol
  ]

  static constraints = {
    code(nullable:false, blank:false);
    description(nullable:true, blank:false);
  }

  static mapping = {
    table 'rs_protocol_action'
    id(column:'pa_id', generator: 'uuid', length:36)
    code column:'pa_code'
    description column:'pa_description'
  }

}

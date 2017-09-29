package com.k_int.folio.rs

import grails.gorm.MultiTenant;

class ProtocolAction  implements MultiTenant<ProtocolAction> {

  String code
  String description

  static belongsTo = [
    protocol:Protocol
  ]

  static constraints = {
    code(nullable:false, blank:false);
    description(nullable:true, blank:false);
  }
}

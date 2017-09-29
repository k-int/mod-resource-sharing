package com.k_int.folio.rs

import grails.gorm.MultiTenant;

class Protocol  implements MultiTenant<Protocol> {

  String code
  String description

  static constraints = {
    code(nullable:false, blank:false);
    description(nullable:true, blank:false);
  }
}

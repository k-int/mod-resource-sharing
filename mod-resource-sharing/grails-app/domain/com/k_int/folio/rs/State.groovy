package com.k_int.folio.rs

import grails.gorm.MultiTenant;


class State  implements MultiTenant<State> {

  String code
  String description

  static belongsTo = [
    owner:StateModel
  ]

  static constraints = {
    code(nullable:false, blank:false);
    description(nullable:true, blank:false);
  }
}

package com.k_int.folio.rs

import grails.gorm.MultiTenant;


class State  implements MultiTenant<State> {

  String id
  String code
  String description

  static belongsTo = [
    owner:StateModel
  ]

  static constraints = {
    code(nullable:false, blank:false);
    description(nullable:true, blank:false);
  }

  static mapping = {
    table 'rs_state'
    id(column:'rss_id', generator: 'uuid', length:36)
    code column:'rss_code'
    description column:'rss_descr'
    owner column:'rss_owner_sm_fk'
  }

}

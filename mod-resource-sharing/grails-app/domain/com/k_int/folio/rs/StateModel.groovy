package com.k_int.folio.rs

import grails.gorm.MultiTenant;


class StateModel  implements MultiTenant<StateModel> {

  String id;
  String code;
  String description;

  static constraints = {
    code(nullable:false, blank:false);
    description(nullable:true, blank:false);
  }

  static mapping = {
    table 'rs_state_model'
    id(column:'rssm_id', generator: 'uuid', length:36)
    code column:'rssm_code'
    description column:'rssm_descr'
  }

}

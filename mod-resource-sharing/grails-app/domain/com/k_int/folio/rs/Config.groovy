package com.k_int.folio.rs

import grails.gorm.MultiTenant


/**
 * Store a persistent JSON block where we hold config
 */
class Config implements MultiTenant<Config> {

  String id
  String code
  String cfg

  private static final MAX_SIZE = 1073741824 // 4GB 

  static constraints = {
    code(nullable:false, blank:false)
    cfg(nullable:false, blank:false,maxSize:MAX_SIZE)
  }

  static mapping = {
    table 'rs_config'
    id(column:'rsc_id', generator: 'uuid', length:36)
    code column:'rsc_code'
     cfg column:'rsc_cfg', type:'text'
  }

}

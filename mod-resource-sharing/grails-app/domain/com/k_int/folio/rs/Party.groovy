package com.k_int.folio.rs

import grails.gorm.MultiTenant;

class Party  implements MultiTenant<Party> {

  String id
  String name

  static hasMany = [
    memberships:Member
  ]

  static mappedBy = [
    memberships:'member'
  ]

  static constraints = {
    name(nullable:false, blank:false);
  }


  static mapping = {
    table 'rs_party'
    id(column:'pty_id', generator: 'uuid')
    name column:'pty_name'
  }

}

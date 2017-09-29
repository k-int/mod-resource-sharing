package com.k_int.folio.rs

import grails.gorm.MultiTenant;

class Party  implements MultiTenant<Party> {

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
}

package com.k_int.folio.rs

class Party {

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

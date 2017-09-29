package com.k_int.folio.rs

import grails.gorm.MultiTenant;

class Member implements MultiTenant<Member> {

  Party owner  // EG The party representing "My Local Consortia"
  Party member // EG The party representing "Library X, who is a member of My Local Consortia"

  static constraints = {
    owner(nullable:false, blank:false);
    member(nullable:false, blank:false);
  }
}

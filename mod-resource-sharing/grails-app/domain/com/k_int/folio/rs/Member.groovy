package com.k_int.folio.rs

import grails.gorm.MultiTenant

class Member implements MultiTenant<Member> {

  String id
  Party owner  // EG The party representing "My Local Consortia"
  Party member // EG The party representing "Library X, who is a member of My Local Consortia"

  static constraints = {
    owner(nullable:false, blank:false);
    member(nullable:false, blank:false);
  }

  static mapping = {
    table 'rs_resource'
    id(column:'rs_id', generator: 'uuid', length:36)
    owner column:'rs_owner_party_fk'
    member column:'rs_member_party_fk'
  }

}

package com.k_int.folio.rs

class Member {

  Party owner  // EG The party representing "My Local Consortia"
  Party member // EG The party representing "Library X, who is a member of My Local Consortia"

  static constraints = {
    owner(nullable:false, blank:false);
    member(nullable:false, blank:false);
  }
}

package com.k_int.folio.rs

class Protocol {

  String code
  String description

  static constraints = {
    code(nullable:false, blank:false);
    description(nullable:true, blank:false);
  }
}

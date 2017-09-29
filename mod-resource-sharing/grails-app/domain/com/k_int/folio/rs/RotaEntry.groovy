package com.k_int.folio.rs

class RotaEntry {

  Integer rotaSequence
  ResourceSharingService service
  State currentState

  static belongsTo = [
    request:ResourceSharingRequest;
  ]

  static constraints = {
    rotaSequence(nullable:false, blank:false)
    service(nullable:false, blank:false)
    currentState(nullable:false, blank:false)
    request(nullable:false, blank:false)
  }
}

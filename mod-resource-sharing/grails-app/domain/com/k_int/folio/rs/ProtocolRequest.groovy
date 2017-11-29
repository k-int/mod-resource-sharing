package com.k_int.folio.rs

import grails.gorm.MultiTenant

/**
 * A single conversation with a remote service to try and negotiate the supply of a described item.
 * For outgoing requests, a ResourceSharingRequest will manage a rota full of many ProtocolRequest objects,
 * moving down the rota until we reach a partner able to supply. For incoming requests, the ProtocolRequest
 * stands alone and tracks our activity responding to such requests.
 */
class ProtocolRequest implements MultiTenant<ProtocolRequest> {

  String id

  String role  // "REQUESTER" or "RESPONDER"

  // rotaSequence is only used for "REQUESTER" role - it represents the position of THIS 
  // request in the sequence of requests sent out for this end user request.
  Integer rotaSequence

  // Who are we communicating with
  ResourceSharingService service

  // What is our current state
  State currentState

  static belongsTo = [
    owner:ResourceSharingRequest
  ]

  static constraints = {
    role(nullable:true, blank:false)
    rotaSequence(nullable:true, blank:false)
    service(nullable:false, blank:false)
    currentState(nullable:false, blank:false)
  }

  static mapping = {
    table 'rs_protocol_request'
    id(column:'pr_id', generator: 'uuid', length:36)
            role column:'pr_role'
    rotaSequence column:'pr_rota_seq'
         service column:'pr_service_fk', cascade: 'merge', 'save-update', 'lock', 'refresh', 'evict', 'replicate'
    currentState column:'pr_current_state_fk', cascade: 'merge', 'save-update', 'lock', 'refresh', 'evict', 'replicate'
           owner column:'pr_owner_rsr_fk'
  }

}

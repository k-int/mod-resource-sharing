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

  // for this request, this is the symbol that we are presenting ourselves as. An organisation
  // may have many symbols, so this is who we are acting as in this request. If the role 
  // of this request is REQUESTER, then this will be the requester symbol. If the role is responder
  // then is will contain the symbol the remote party used for US
  SymbolService localSymbolService

  // The symbol of the remote party. For REQUESTER instances, this is the RESPONDER symbol, for
  // RESPONDER instances, this is the symbol of the requesting party.
  SymbolService remoteSymbolService

  // What is our current state
  State currentState

  static belongsTo = [
    owner:ResourceSharingRequest
  ]

  static constraints = {
    role(nullable:true, blank:false)
    rotaSequence(nullable:true, blank:false)
    localSymbolService(nullable:false, blank:false)
    remoteSymbolService(nullable:false, blank:false)
    currentState(nullable:false, blank:false)
  }

  static mapping = {
    table 'rs_protocol_request'
    id(column:'pr_id', generator: 'uuid', length:36)
                   role column:'pr_role'
           rotaSequence column:'pr_rota_seq'
     localSymbolService column:'pr_local_symbol_service_fk', cascade: 'merge', 'save-update', 'lock', 'refresh', 'evict', 'replicate'
    remoteSymbolService column:'pr_remote_symbol_service_fk', cascade: 'merge', 'save-update', 'lock', 'refresh', 'evict', 'replicate'
           currentState column:'pr_current_state_fk', cascade: 'merge', 'save-update', 'lock', 'refresh', 'evict', 'replicate'
                  owner column:'pr_owner_rsr_fk'
  }

}

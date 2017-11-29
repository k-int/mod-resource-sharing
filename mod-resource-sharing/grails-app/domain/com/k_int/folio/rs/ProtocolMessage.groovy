package com.k_int.folio.rs

import grails.gorm.MultiTenant;

/**
 * A specific instance of a protocol message passed between 2 parties acting in the role of requester and responder.
 * A message is an action that will cause a transition between 2 protocol states (Tho they can be the same state)
 * this object records the detail of the message.
 */
class ProtocolMessage implements MultiTenant<ProtocolMessage> {

  String id
  Date messageTimestamp
  boolean valid
  String messageContent

  // The protocol transition that this message represents
  Transition transition

  static belongsTo = [
    owner:ProtocolRequest
  ]

  static constraints = {
    messageTimestamp(nullable:false, blank:false)
    valid(nullable:false, blank:false)
    transition(nullable:true, blank:false)
    messageContent(nullable:true, blank:false)
  }

  static mapping = {
    table 'rs_protocol_message'
    id(column:'pm_id', generator: 'uuid', length:36)
    messageTimestamp column:'pm_message_timestamp'
               valid column:'pm_valid'
      messageContent column:'pm_message_content'
          transition column:'pm_transition'
               owner column:'pm_owner_id'
  }

}

package com.k_int.folio.rs

/**
 * A specific instance of a protocol message passed between 2 parties acting in the role of requester and responder.
 * A message is an action that will cause a transition between 2 protocol states (Tho they can be the same state)
 * this object records the detail of the message.
 */
class ProtocolMessage {

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
}

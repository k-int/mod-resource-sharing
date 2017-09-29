package com.k_int.folio.rs

import grails.gorm.MultiTenant;


class Transition  implements MultiTenant<Transition> {

  String id
  State to
  State from
  ProtocolAction action

  static constraints = {
  }

  static mapping = {
    table 'rs_transition'
    id(column:'rst_id', generator: 'uuid', length:36)
    to column:'rst_to_state_fk'
    from column:'rst_from_state_fk'
    action column:'rst_action_fk'
  }

}

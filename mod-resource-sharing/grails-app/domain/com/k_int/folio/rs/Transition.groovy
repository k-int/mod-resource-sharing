package com.k_int.folio.rs

import grails.gorm.MultiTenant;


class Transition  implements MultiTenant<Transition> {

  State to
  State from
  ProtocolAction action

  static constraints = {
  }
}

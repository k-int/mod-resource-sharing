package com.k_int.folio.rs

import grails.events.annotation.Subscriber

class RotaListenerService {

  @Subscriber
  void onRotastart(String rsrId) {
    log.info "Rota started for ${rsrId}"
  }

  @Subscriber
  void onRotanotsupplied(String rsrId, String prId) {
    log.info "Institution ID ${prId} could not supply for request ${rsrId}"
  }

  @Subscriber
  void onRotashipped(String rsrId, String prId) {
    log.info "Institution ID ${prId} has shipped for request ${rsrId}"
  }

  @Subscriber
  void onRotapending(String rsrId, String prId) {
    log.info "Pending response from Institution ID ${prId} for request ${rsrId}"
  }

  @Subscriber
  void onRotaend(String rsrId) {
    log.info "Rota ended for ${rsrId}"
  }
}

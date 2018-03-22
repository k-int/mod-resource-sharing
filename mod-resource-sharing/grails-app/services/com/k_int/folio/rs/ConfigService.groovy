package com.k_int.folio.rs

import grails.events.Event
import grails.events.annotation.Subscriber
import grails.gorm.multitenancy.Tenants

class ConfigService {
  
  @Subscriber("okapi:schema_update")
  void bootstrap(String tenantName, String tenantId) {
//    String tenant = e.data
    log.debug('Caught the event OKAPI:schema_update')
    log.debug("TenantAdminService::bootstrap(${tenantName}, ${tenantId})")
    Tenants.withId(tenantId) {
      // updated
      log.debug("TenantAdminService::bootstrap withId(${tenantId})");
      Config c = Config.findByCode('default') ?: (new Config(code:'default', cfg:'{}').save(flush:true, failOnError:true))
    }
    log.debug("bootstrap(${tenantId})");
  }
}

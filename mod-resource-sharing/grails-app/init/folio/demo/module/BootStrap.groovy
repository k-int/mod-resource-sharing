package folio.demo.module

import grails.gorm.multitenancy.*

class BootStrap {

  def grailsApplication
  def tenantAdminService
  def dataSource
  def rulesService

  def init = { servletContext ->
    log.debug("Reporting config from folio_globals.yaml: ${grailsApplication.config.testsection.message}");
    
    // Freshen up the schemas.
    tenantAdminService.createTenant('diku')
    tenantAdminService.freshenAllTenantSchemas()
    
    log.debug("BootStrap::init completed");
  }


  def destroy = {
  }

}

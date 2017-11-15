package folio.demo.module

import grails.gorm.multitenancy.*

class BootStrap {

  def grailsApplication
  def tenantAdminService
  def dataSource

  def init = { servletContext ->
    log.debug("Reporting config from folio_globals.yaml: ${grailsApplication.config.testsection.message}");
    
    // Create the diku tenant. This isn't how the final solution will work.
    tenantAdminService.createTenant('diku')
    tenantAdminService.freshenAllTenantSchemas()
    
    log.debug("BootStrap::init completed");
  }


  def destroy = {
  }

}

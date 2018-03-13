package folio.demo.module

import grails.gorm.multitenancy.*

class BootStrap {

  def grailsApplication
  def tenantAdminService
  def dataSource
  def rulesService

  def init = { servletContext ->
    log.debug("Reporting config from folio_globals.yaml: ${grailsApplication.config.testsection.message}");
 
    // Call freshenModuleSchema to update any shared tables that live at the module level. For mod-resource-sharing
    // this is the symbol to tenant id mapping
    tenantAdminService.freshenModuleSchema()

    // Freshen up the schemas.
    tenantAdminService.createTenant('diku')
    tenantAdminService.freshenAllTenantSchemas()
    
    log.debug("BootStrap::init completed");
  }


  def destroy = {
  }

}

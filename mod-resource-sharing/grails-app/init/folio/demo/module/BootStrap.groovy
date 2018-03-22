package folio.demo.module

import com.k_int.okapi.OkapiTenantAdminService

class BootStrap {

  def grailsApplication
  OkapiTenantAdminService okapiTenantAdminService

  def init = { servletContext ->
    log.debug("Reporting config from folio_globals.yaml: ${grailsApplication.config.testsection.message}");
    
    // Freshen up the schemas.
    okapiTenantAdminService.createTenant('diku')
    okapiTenantAdminService.freshenAllTenantSchemas()
    
    log.debug("BootStrap::init completed");
  }


  def destroy = {
  }

}

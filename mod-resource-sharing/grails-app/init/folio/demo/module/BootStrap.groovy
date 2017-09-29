package folio.demo.module

import org.grails.plugins.databasemigration.liquibase.GrailsLiquibase
import javax.sql.DataSource
import grails.gorm.multitenancy.*

class BootStrap {

  def grailsApplication
  def tenantAdminService
  def dataSource

  def init = { servletContext ->
    log.debug("Reporting config from folio_globals.yaml: ${grailsApplication.config.testsection.message}");

    log.debug("BootStrap::init completed");
  }


  def destroy = {
  }

}

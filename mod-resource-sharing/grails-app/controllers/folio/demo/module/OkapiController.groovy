package folio.demo.module

import groovy.sql.Sql

import grails.rest.*
import grails.converters.*
import javax.sql.DataSource
import liquibase.Liquibase
import liquibase.database.Database
import org.grails.plugins.databasemigration.liquibase.GrailsLiquibase

class OkapiController {

  static responseFormats = ['json', 'xml']
  def hibernateDatastore
  def dataSource
  def tenantAdminService


  def index() { 
  }

  // GET And DELETE verbs with header X-Okapi-Tenant indicate activation of this module for a given tenant.
  def tenant() {

    String tenant_id = request.getHeader('X-Okapi-Tenant')?.toLowerCase()

    log.info("OkapiController::tenant ${request.method} ${params} ${tenant_id}");
    def result = [:]
    if ( tenant_id && tenant_id.trim().length() > 0 ) {
      switch ( request.method ) {
        case 'GET':
        case 'POST':
          tenantAdminService.createTenant(tenant_id);
          break;
        case 'DELETE':
          log.debug("Request to destroy tenant -- hanging fire here");
          // This is well risqe, but it actually suits our functional test framework ;)
          tenantAdminService.dropTenant(tenant_id);
          break;
        default:
          log.warn("Unhandled verb ${request.method} for module /_/tenant endpoint");
          break;
      }
    }
    else {
      throw new RuntimeException("No X-Okapi-Tenant header");
    }

    render result as JSON
  }

}

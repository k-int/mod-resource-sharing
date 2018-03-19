package com.k_int.okapi

import groovy.sql.Sql
import grails.rest.*
import grails.converters.*
import grails.core.GrailsApplication
import javax.sql.DataSource
import liquibase.Liquibase
import liquibase.database.Database
import org.grails.plugins.databasemigration.liquibase.GrailsLiquibase
import java.sql.Connection
import java.sql.ResultSet
import org.grails.datastore.gorm.jdbc.schema.SchemaHandler
import grails.gorm.multitenancy.Tenants
import com.k_int.folio.rs.Config

class TenantAdminService {

  final static String SCHEMA_SUFFIX = '_mod_resource_sharing'

  def hibernateDatastore
  def dataSource
  GrailsApplication grailsApplication

  public void createTenant(String tenantId) {

      String new_schema_name = tenantId+SCHEMA_SUFFIX;
      try {
        log.debug("See if we already have a datastore for ${new_schema_name}");
        hibernateDatastore.getDatastoreForConnection(new_schema_name)
        log.debug("Module already registered for tenant");
      }
      catch ( org.grails.datastore.mapping.core.exceptions.ConfigurationException ce ) {
        log.debug("register module for tenant/schema (${tenantId}/${new_schema_name})");
        createAccountSchema(new_schema_name);
        updateAccountSchema(new_schema_name);

        hibernateDatastore.addTenantForSchema(new_schema_name)
      }
  }

  void createAccountSchema(String tenantId) {
    Sql sql = null
    try {
        sql = new Sql(dataSource as DataSource)
        sql.withTransaction {
            log.debug("Execute -- create schema ${tenantId}");
            sql.execute("create schema ${tenantId}" as String)
        }
    } finally {
        sql?.close()
    }
  }

  void dropTenant(String tenantId) {
    log.debug("TenantAdminService::dropTenant(${tenantId})");
    Sql sql = null
    String schema_name = tenantId+SCHEMA_SUFFIX;
    try {
        sql = new Sql(dataSource as DataSource)
        sql.withTransaction {
            log.debug("Execute -- drop schema ${schema_name} cascade");
            sql.execute("drop schema ${schema_name} cascade" as String)
        }
    } finally {
        sql?.close()
    }
  }

  void freshenAllTenantSchemas() {
    log.debug("freshenAllTenantSchemas()");
    ResultSet schemas = dataSource.getConnection().getMetaData().getSchemas()
    while(schemas.next()) {
      String schema_name = schemas.getString("TABLE_SCHEM")
      if ( schema_name.endsWith(SCHEMA_SUFFIX) ) {
        updateAccountSchema(schema_name)
      }
      else {
        log.debug("${schema_name} does not end with schema suffux ${SCHEMA_SUFFIX}");
      }
    }
  }

  void updateAccountSchema(String schema_name) {

    log.debug("updateAccountSchema(${schema_name})");

    // Now try create the tables for the schema
    try {
      GrailsLiquibase gl = new GrailsLiquibase(grailsApplication.mainContext)
      gl.dataSource = dataSource
      gl.dropFirst = false
      gl.changeLog = 'module-tenant-changelog.groovy'
      gl.contexts = []
      gl.labels = []
      gl.defaultSchema = schema_name
      gl.databaseChangeLogTableName = 'mod_resource_sharing_tenant_changelog'
      gl.databaseChangeLogLockTableName = 'mod_resource_sharing_tenant_changelog_lock'
      gl.afterPropertiesSet() // this runs the update command
    } catch (Exception e) {
      log.error("Exception trying to create new account schema tables for $schema_name", e)
      throw e
    }
    finally {
      log.debug("Database migration completed");
    }

    try {
      log.debug("adding tenant for ${schema_name}");
      hibernateDatastore.addTenantForSchema(schema_name)
      bootstrap(schema_name);
    } catch (Exception e) {
      log.error("Exception adding tenant schema for ${schema_name}", e)
      throw e
    }
    finally {
      log.debug("added schema");
    }
  }

  void bootstrap(tenant) {
    log.debug("TenantAdminService::bootstrap(${tenant})");
    Tenants.withId(tenant) {
      // updated
      log.debug("TenantAdminService::bootstrap withId(${tenant})");
      Config c = Config.findByCode('default') ?: new Config(code:'default', cfg:'{}').save(flush:true, failOnError:true)
    }
    log.debug("bootstrap(${tenant})");
  }

}

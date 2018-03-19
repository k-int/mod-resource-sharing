package com.k_int.okapi

import javax.servlet.http.HttpServletRequest

import org.grails.datastore.mapping.multitenancy.TenantResolver
import org.grails.datastore.mapping.multitenancy.exceptions.TenantNotFoundException
import org.springframework.web.context.request.RequestAttributes
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletWebRequest

import com.k_int.okapi.OkapiHeaders

import grails.util.Holders
import groovy.transform.CompileStatic
import groovy.transform.TypeCheckingMode

/**
 * A tenant resolver that resolves the tenant from the request HTTP Header
 */
@CompileStatic
class OkapiTenantResolver implements TenantResolver {
  
  public static final String getTenantSchemaName ( String tenantId ) {
    "${tenantId}${getSchemaSuffix()}"
  }
  
  public static final String getSchemaSuffix () {
    "_${getSchemaAppName()}"
  }
  
  @CompileStatic(TypeCheckingMode.SKIP)
  public static final String getSchemaAppName () {
    "${Holders.grailsApplication.config.info.app.name.replaceAll(/\s/,'').replaceAll(/-/,'_').toLowerCase()}"
  }
  
  @Override
  Serializable resolveTenantIdentifier() {

    RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes()
    if(requestAttributes instanceof ServletWebRequest) {
      HttpServletRequest httpServletRequest = ((ServletWebRequest) requestAttributes).getRequest()
      String tenantId = httpServletRequest.getHeader(OkapiHeaders.TENANT.toLowerCase())?.toLowerCase()

      if ( tenantId ) {
        return getTenantSchemaName(tenantId)
      }
      throw new TenantNotFoundException("Tenant could not be resolved from HTTP Header: ${OkapiHeaders.TENANT}")
    }

    throw new TenantNotFoundException("Tenant could not be resolved outside a web request")
  }
}

package folio.demo.module

import org.springframework.beans.factory.config.RuntimeBeanReference
import org.springframework.boot.autoconfigure.security.Http401AuthenticationEntryPoint
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.security.web.access.AccessDeniedHandler
import com.k_int.okapi.springsecurity.OkapiAuthAwareAccessDeniedHandler
import com.k_int.okapi.springsecurity.OkapiAuthenticationFilter
import com.k_int.okapi.springsecurity.OkapiAuthenticationProvider

import grails.boot.GrailsApp
import grails.boot.config.GrailsAutoConfiguration
import grails.plugin.springsecurity.SecurityFilterPosition
import grails.plugin.springsecurity.SpringSecurityUtils

class Application extends GrailsAutoConfiguration {
  
  static void main(String[] args) {
    GrailsApp.run(Application, args)
  }
  
  @Override
  Closure doWithSpring() {{->
    // Change the authentication end point to throw a 401
    authenticationEntryPoint(Http401AuthenticationEntryPoint, 'realm="Resource Sharing"')
      
    // This filter registers itself in a particular order in the chain.
    okapiAuthenticationFilter(OkapiAuthenticationFilter){ bean ->
      authenticationManager = ref('authenticationManager')
    }
    
    // Because of the custom ordering above we need to stop this from being de/registered automatically.
    okapiAuthenticationFilterDeregistrationBean(FilterRegistrationBean) {
      filter = ref('okapiAuthenticationFilter')
      enabled = false
    }
    
    okapiAuthenticationProvider(OkapiAuthenticationProvider)
    
    // Replace the AccessDenied handler to not redirect if the authentication was done with OKAPI.
    okapiAuthAwareAccessDeniedHandler(OkapiAuthAwareAccessDeniedHandler)
  }}
  
  @Override
  void doWithApplicationContext() {
    
    // Register this filter first.
    SpringSecurityUtils.clientRegisterFilter(
      'okapiAuthenticationFilter', SecurityFilterPosition.FIRST)
  }
}

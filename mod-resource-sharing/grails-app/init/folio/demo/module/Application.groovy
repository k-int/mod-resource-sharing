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
import org.apache.commons.lang.exception.ExceptionUtils
import org.grails.events.bus.*
import java.util.concurrent.*
class Application extends GrailsAutoConfiguration {
  
  static void main(String[] args) {
    ExceptionUtils d
    Thread.setDefaultUncaughtExceptionHandler(
      new Thread.UncaughtExceptionHandler() {
        @Override public void uncaughtException(Thread t, Throwable e) {
          System.out.println(ExceptionUtils.getFullStackTrace(e));
        }
      }
    );
    GrailsApp.run(Application, args)
  }
}

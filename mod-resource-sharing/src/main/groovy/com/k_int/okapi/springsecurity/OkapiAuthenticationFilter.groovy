package com.k_int.okapi.springsecurity

import java.io.IOException

import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter

import com.k_int.springsecurity.RestfulAuthenticationSuccessHandler

import groovy.json.JsonSlurper
import okapi.OkapiHeaders

class OkapiAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
  
  private final JsonSlurper json = new JsonSlurper()
  public OkapiAuthenticationFilter() {
    super("/**")
    this.authenticationSuccessHandler = new RestfulAuthenticationSuccessHandler()
    this.allowSessionCreation = false
  }
  
  /* 
   * This is probably not the final implementation of this. We aren't actually authenticating here but checking that headers exists and providing a token
   * if they do. Obviously authenticating based on header existence isn't secure and so doesn't actually provide any security, but merely serves as a place
   * to allow us to set the various claims and limit controllers and actions based on them using the spring security mechanism.
   * 
   * 
   * @see org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter#attemptAuthentication(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
   */
  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    
    String tenant = request.getHeader(OkapiHeaders.TENANT)
    String token = request.getHeader(OkapiHeaders.TOKEN)
    String userId = request.getHeader(OkapiHeaders.USER_ID)
    String grantsStr = request.getHeader(OkapiHeaders.PERMISSIONS) ?: '[]'

    if (!(tenant && token && userId && grantsStr)) {
      // Not OKAPI request. Return null to exit.
      return null
    }
    
    // Parse the grants as JSON.
    def grants = json.parseText(grantsStr)
  
    OkapiAuthenticationToken authRequest = new OkapiAuthenticationToken(
      userId,
      grants.collect { new SimpleGrantedAuthority("folio.${it}") }
    )

    getAuthenticationManager().authenticate(authRequest)
  }
  
  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
  throws IOException, ServletException {
    super.successfulAuthentication(request, response, chain, authResult)

    // As we are authenticating using header information, we need to continue with the filter chain to ensure the request is
    // properly actioned.
    chain.doFilter(request, response)
  }
}

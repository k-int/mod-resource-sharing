package com.k_int.folio.rs


import grails.testing.mixin.integration.Integration
import grails.transaction.*
import static grails.web.http.HttpHeaders.*
import static org.springframework.http.HttpStatus.*
import spock.lang.*
import geb.spock.*
import grails.plugins.rest.client.RestBuilder

@Integration
@Rollback
class RSRequestSpec extends GebSpec {

    def setup() {
    }

    def cleanup() {
    }


    // Set up a new tenant called RSTestTenant
    void "Set up test tenant"() {
        when:"We post a new tenant request to the OKAPI controller"
            def resp = restBuilder().post("$baseUrl/_/tenant") {
              header 'X-Okapi-Tenant', 'RSTestTenant'
            }

        then:"The response is correct"
            resp.status == OK.value()
            // resp.headers[CONTENT_TYPE] == ['application/json;charset=UTF-8']
            // resp.json.message == 'Welcome to Grails!'
    }

    void "Set up some test locations"(code,name) {
      expect:
        def resp = restBuilder().post("$baseUrl/locations") {
          header 'X-Okapi-Tenant', 'RSTestTenant'
          contentType "application/json"
          json code: code, name: name
        }
        resp.status == OK.value()

      // Use a GEB Data Table to load each record
      where:
        code | name
        'croo' | 'Crookes community library'
        'stan' | 'Stannington Library'
        'upper' | 'Upperthorpe Library'
    }

    void "Delete the tenant"() {
        when:"We post a delete request to the OKAPI controller"
            def resp = restBuilder().delete("$baseUrl/_/tenant") {
              header 'X-Okapi-Tenant', 'RSTestTenant'
            }

        then:"The response is correct"
            resp.status == OK.value()
            // resp.headers[CONTENT_TYPE] == ['application/json;charset=UTF-8']
    }

    RestBuilder restBuilder() {
        new RestBuilder()
    }
}

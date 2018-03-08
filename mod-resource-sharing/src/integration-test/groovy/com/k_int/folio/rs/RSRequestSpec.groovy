package com.k_int.folio.rs


import grails.testing.mixin.integration.Integration
import grails.transaction.*
import static grails.web.http.HttpHeaders.*
import static org.springframework.http.HttpStatus.*
import spock.lang.*
import geb.spock.*
import grails.plugins.rest.client.RestBuilder

@Integration
// @Rollback
@Stepwise
class RSRequestSpec extends GebSpec {

    def setup() {
    }

    def cleanup() {
    }


    // Set up a new tenant called RSTestTenantA
    void "Set up test tenant A"() {
        when:"We post a new tenant request to the OKAPI controller"
            def resp = restBuilder().post("$baseUrl/_/tenant") {
              header 'X-Okapi-Tenant', 'RSTestTenantA'
            }

        then:"The response is correct"
            resp.status == OK.value()
            // resp.headers[CONTENT_TYPE] == ['application/json;charset=UTF-8']
            // resp.json.message == 'Welcome to Grails!'
    }

    // Set up a new tenant called RSTestTenantB
    void "Set up test tenant B"() {
        when:"We post a new tenant request to the OKAPI controller"
            def resp = restBuilder().post("$baseUrl/_/tenant") {
              header 'X-Okapi-Tenant', 'RSTestTenantB'
            }

        then:"The response is correct"
            resp.status == OK.value()
            // resp.headers[CONTENT_TYPE] == ['application/json;charset=UTF-8']
            // resp.json.message == 'Welcome to Grails!'
    }


    void "Set up some test locations"(tenant,code,name) {
      expect:

        def json_location = [ 'code' : code, 'name' : name ]
        def resp = restBuilder().post("$baseUrl/locations") {
          header 'X-Okapi-Tenant', 'RSTestTenantA'
          contentType 'application/json'
          json json_location
        }
        System.err.println("RESPONSE:: ${resp.json}");
        resp.status == OK.value()
        

      // Use a GEB Data Table to load each record
      where:
        tenant | code | name
        'RSTestTenantA' | 'croo' | 'Crookes community library'
        'RSTestTenantA' | 'stan' | 'Stannington Library'
        'RSTestTenantA' | 'upper' | 'Upperthorpe Library'
        'RSTestTenantA' | 'TestA' | 'Main Library for Test Tenant A'
        'RSTestTenantA' | 'TestB' | 'Main Library for Test Tenant B'
        'RSTestTenantB' | 'croo' | 'Crookes community library'
        'RSTestTenantB' | 'stan' | 'Stannington Library'
        'RSTestTenantB' | 'upper' | 'Upperthorpe Library'
        'RSTestTenantB' | 'TestA' | 'Main Library for Test Tenant A'
        'RSTestTenantB' | 'TestB' | 'Main Library for Test Tenant B'
    }

    void "User Makes a request"() {

      when: "We submit a new request"
        def request_details = [
          itemType:'serial',
          title:'Americal Libraries',
          subTitle:'THE MAGAZINE OF THE AMERICAN LIBRARY ASSOCIATION',
          volume:'48',
          issue:'3/4',
          issn:'0002-9769',
          pagination:'p16',
          titleOfArticle:'Terri Grief',
          patronId:'1234-5678-1234-5533-4545'
        ]

        def resp = restBuilder().post("$baseUrl/requests") {
          header 'X-Okapi-Tenant', 'RSTestTenantA'
          contentType 'application/json'
          json request_details
        }

      then: "System creates a new request record"
        System.err.println("RESPONSE:: ${resp.json}");
        resp.status == OK.value()
    }

    void "User lists their requests"() {
      when:"We ask the system to list requests for our user"
        def resp = restBuilder().get("$baseUrl/requests/search?q=1234-5678-1234-5533-4545") {
          header 'X-Okapi-Tenant', 'RSTestTenantA'
        }

      then: "The system responds with the request we created above"
        resp.status == OK.value()

    }

    void "Delete the tenant"() {
        when:"We post a delete request to the OKAPI controller"
            def resp = restBuilder().delete("$baseUrl/_/tenant") {
              header 'X-Okapi-Tenant', 'RSTestTenantA'
            }

        then:"The response is correct"
            resp.status == OK.value()
            // resp.headers[CONTENT_TYPE] == ['application/json;charset=UTF-8']
    }

    RestBuilder restBuilder() {
        new RestBuilder()
    }
}

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

        def json_location = [ 'code' : code, 'name' : name ]
        def resp = restBuilder().post("$baseUrl/locations") {
          header 'X-Okapi-Tenant', 'RSTestTenant'
          contentType 'application/json'
          json json_location
        }
        System.err.println("RESPONSE:: ${resp.json}");
        resp.status == CREATED.value()
        

      // Use a GEB Data Table to load each record
      where:
        code | name
        'croo' | 'Crookes community library'
        'stan' | 'Stannington Library'
        'upper' | 'Upperthorpe Library'
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
          header 'X-Okapi-Tenant', 'RSTestTenant'
          contentType 'application/json'
          json request_details
        }

      then: "System creates a new request record"
        System.err.println("RESPONSE:: ${resp.json}");
        resp.status == CREATED.value()
    }

    void "User lists their requests"() {
      when:"We ask the system to list requests for our user"
        def resp = restBuilder().get("$baseUrl/requests/search?q=1234-5678-1234-5533-4545") {
          header 'X-Okapi-Tenant', 'RSTestTenant'
        }

      then: "The system responds with the request we created above"
        resp.status == OK.value()

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

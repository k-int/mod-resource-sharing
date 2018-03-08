package com.k_int.folio.rs


import grails.testing.mixin.integration.Integration
import grails.transaction.*
import static grails.web.http.HttpHeaders.*
import static org.springframework.http.HttpStatus.*
import spock.lang.*
import geb.spock.*
import grails.plugins.rest.client.RestBuilder
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import okapi.OkapiHeaders

@Integration
// @Rollback
@Stepwise
class RSRequestSpec extends GebSpec {

  final static Logger logger = LoggerFactory.getLogger(RSRequestSpec.class);

    def setup() {
    }

    def cleanup() {
    }


    // Set up a new tenant called RSTestTenantA
    void "Set up test tenant A"() {
        when:"We post a new tenant request to the OKAPI controller"
            def resp = restBuilder().post("$baseUrl/_/tenant") {
              header 'X-Okapi-Tenant', 'RSTestTenantA'
              header OkapiHeaders.TOKEN, 'dummy'
              header OkapiHeaders.USER_ID, 'dummy'
              header OkapiHeaders.PERMISSIONS, '["resource-sharing.admin","rs-admin"]'
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
              header OkapiHeaders.TOKEN, 'dummy'
              header OkapiHeaders.USER_ID, 'dummy'
              header OkapiHeaders.PERMISSIONS, '["resource-sharing.admin","rs-admin"]'
            }

        then:"The response is correct"
            resp.status == OK.value()
            // resp.headers[CONTENT_TYPE] == ['application/json;charset=UTF-8']
            // resp.json.message == 'Welcome to Grails!'
    }


    /**
     * the RS module allows institutions to share resources. Institutions are modelled as party entities.
     * A party can have many symbols. here we set up in each of our two tennants records that describe
     * ourselves and our remote parties. Our test setup establishes two tenants each of which know about 
     * themselves and the other party.
     */
    void "Set up some test organisations"(tenant,code,name) {

      expect:

        def json_location = [ 'code' : code, 'name' : name ]

        def resp = restBuilder().post("$baseUrl/locations") {
          header 'X-Okapi-Tenant', 'RSTestTenantA'
          header OkapiHeaders.TOKEN, 'dummy'
          header OkapiHeaders.USER_ID, 'dummy'
          header OkapiHeaders.PERMISSIONS, '["resource-sharing.admin","rs-admin"]'
          contentType 'application/json'
          accept 'application/json'
          json json_location
        }
 
        logger.debug("Create Party Response:: ${resp} ${resp.json}");

        resp.status == CREATED.value()
        

      // Use a GEB Data Table to load each record
      where:
        tenant | code | name
        'RSTestTenantA' | 'TestA' | 'Main Library for Test Tenant A'
        'RSTestTenantA' | 'TestB' | 'Main Library for Test Tenant B'
        'RSTestTenantB' | 'TestA' | 'Main Library for Test Tenant A'
        'RSTestTenantB' | 'TestB' | 'Main Library for Test Tenant B'
    }

    /**
     * An institution can have many symbols, but here we just set up defaults. Our two institutions, TestA and TestB
     * both have 1 symbol testa and testb. TestA also has an alternate symbol.
     */
    void "Set up some test symbols"(tenant,party,code,name) {

      expect:

        // Retrieve the json list of all locations this tenant knows about
        def location_info = restBuilder().get("$baseUrl/locations/index") {
          header 'X-Okapi-Tenant', tenant
          header OkapiHeaders.TOKEN, 'dummy'
          header OkapiHeaders.USER_ID, 'dummy'
          header OkapiHeaders.PERMISSIONS, '["resource-sharing.admin","rs-admin"]'
          contentType 'application/json'
          accept 'application/json'
        }

        logger.info("Location data: ${location_info}");

        // def resp = restBuilder().post("$baseUrl/locations") {
        //   header 'X-Okapi-Tenant', 'RSTestTenantA'
        //   contentType 'application/json'
        //   json json_location
        // }
        // System.err.println("RESPONSE:: ${resp.json}");
        // resp.status == OK.value()
        1==1;
        

      // Use a GEB Data Table to load each record
      where:
        tenant | party | code | name
        'RSTestTenantA' | 'TestA' | 'testa'      | 'Org Test A, central library symbol'
        'RSTestTenantA' | 'TestA' | 'testa-alt1' | 'Org Test A, Annex Library'
        'RSTestTenantA' | 'TestB' | 'testb'      | 'Org Test B, central library symbol'
        'RSTestTenantB' | 'TestA' | 'testa'      | 'Org Test A, central library symbol'
        'RSTestTenantB' | 'TestA' | 'testa-alt1' | 'Org Test A, Annex Library'
        'RSTestTenantB' | 'TestB' | 'testb'      | 'Org Test B, central library symbol'
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
          header OkapiHeaders.TOKEN, 'dummy'
          header OkapiHeaders.USER_ID, 'dummy'
          header OkapiHeaders.PERMISSIONS, '["resource-sharing.admin"]'
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
          header 'X-Okapi-Tenant', 'RSTestTenantA'
        }

      then: "The system responds with the request we created above"
        resp.status == OK.value()

    }

    void "Delete the tenants"(tenant_id, note) {

        expect:"post delete request to the OKAPI controller for "+tenant_id+" results in OK and deleted tennant"

            def resp = restBuilder().delete("$baseUrl/_/tenant") {
              header 'X-Okapi-Tenant', tenant_id
              header OkapiHeaders.TOKEN, 'dummy'
              header OkapiHeaders.USER_ID, 'dummy'
              header OkapiHeaders.PERMISSIONS, '["resource-sharing.admin"]'
            }

            resp.status == OK.value()

        where:
          tenant_id | note
          'RSTestTenantA' | 'note'
          'RSTestTenantB' | 'note'
    }

    RestBuilder restBuilder() {
        new RestBuilder()
    }
}

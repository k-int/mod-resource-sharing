#!/bin/bash


echo create some tenants - This won't work currently because 

curl --header "X-Okapi-Tenant: RSTestTenantA" http://localhost:8080/hello/index -X GET
curl --header "X-Okapi-Tenant: RSTestTenantA" http://localhost:8080/_/tenant -X POST

# public static final String OKAPI_URL_HEADER = "X-Okapi-URL";
# public static final String OKAPI_TOKEN_HEADER = "X-Okapi-Token";
# public static final String OKAPI_TENANT_HEADER = "X-Okapi-Tenant";
# public static final String OKAPI_PERMISSIONS_HEADER = "X-Okapi-Permissions";
# curl -i \
#     -H "Accept: application/json" \
#     -H "X-HTTP-Method-Override: PUT" \
#     -X POST \
#      http://localhost:8080/xx/xxx/xxxx

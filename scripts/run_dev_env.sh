#!/bin/bash

# If you're running okapi from vagrant, you can ask okapi to proxy though directly to the grails run-app command

# curl -i -w '\n' -X GET http://localhost:9130/_/proxy/modules

# Register the module descriptor as usual

# Register a deployment descriptor with no node, and a URI of the running app.

echo Register module descriptor
curl -i -w '\n' -X POST -H 'Content-type: application/json' -d @../mod-resource-sharing/src/okapi/ModuleDescriptor-template.json http://localhost:9130/_/proxy/modules

echo Register Launch descriptor -- URI version

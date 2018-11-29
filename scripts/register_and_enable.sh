BASEDIR=$(dirname "$0")
# echo Please make sure you have run ./gradlew clean generateDescriptors before starting this script
pushd "$BASEDIR/../mod-resource-sharing"

# Check for decriptor target directory.

DESCRIPTORDIR="build/resources/main/okapi"

if [ ! -d "$DESCRIPTORDIR" ]; then
    echo "No descriptors found. Let's try building them."
    ./gradlew generateDescriptors
fi

curl -XDELETE http://localhost:9130/_/proxy/tenants/diku/modules/mod-resource-sharing-1.0.7
curl -XDELETE http://localhost:9130/_/discovery/modules/mod-resource-sharing-1.0.7/localhost-mod-resource-sharing-1.0.7
curl -XDELETE http://localhost:9130/_/proxy/modules/mod-resource-sharing-1.0.7
# ./gradlew clean generateDescriptors
curl -XPOST http://localhost:9130/_/proxy/modules -d @"${DESCRIPTORDIR}/ModuleDescriptor.json"
curl -XPOST http://localhost:9130/_/discovery/modules -d @"${DESCRIPTORDIR}/DeploymentDescriptor.json"
curl -XPOST http://localhost:9130/_/proxy/tenants/diku/modules -d '{"id": "mod-resource-sharing-1.0.7"}'
popd

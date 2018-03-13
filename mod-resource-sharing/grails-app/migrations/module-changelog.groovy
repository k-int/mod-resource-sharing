databaseChangeLog = {

    changeSet(author: "ibbo (generated)", id: "1520937236926-1") {
        createTable(tableName: "rs_tenant_mapping") {
            column(name: "rstm_id", type: "VARCHAR(36)") {
                constraints(nullable: "false")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "rstm_tenant_id", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "rstm_symbol", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

   changeSet(author: "ibbo (generated)", id: "1520937236926-2") {
        addPrimaryKey(columnNames: "rstm_id", constraintName: "rs_tenant_mappingPK", tableName: "rs_tenant_mapping")
    }

}

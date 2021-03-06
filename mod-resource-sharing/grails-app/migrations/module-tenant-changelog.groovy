databaseChangeLog = {

    changeSet(author: "ibbo (generated)", id: "1520699228049-1") {
        createTable(tableName: "rs_config") {
            column(name: "rsc_id", type: "VARCHAR(36)") {
                constraints(nullable: "false")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "rsc_cfg", type: "CLOB") {
                constraints(nullable: "false")
            }

            column(name: "rsc_code", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "ibbo (generated)", id: "1520699228049-2") {
        createTable(tableName: "rs_naming_authority") {
            column(name: "rsna_id", type: "VARCHAR(36)") {
                constraints(nullable: "false")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "shortcode", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "ibbo (generated)", id: "1520699228049-3") {
        createTable(tableName: "rs_party") {
            column(name: "pty_id", type: "VARCHAR(36)") {
                constraints(nullable: "false")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "pty_code", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "pty_name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "ibbo (generated)", id: "1520699228049-4") {
        createTable(tableName: "rs_protocol") {
            column(name: "prot_id", type: "VARCHAR(36)") {
                constraints(nullable: "false")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "prot_code", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "prot_description", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "ibbo (generated)", id: "1520699228049-5") {
        createTable(tableName: "rs_protocol_action") {
            column(name: "pa_id", type: "VARCHAR(36)") {
                constraints(nullable: "false")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "protocol_id", type: "VARCHAR(36)") {
                constraints(nullable: "false")
            }

            column(name: "pa_code", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "pa_description", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "ibbo (generated)", id: "1520699228049-6") {
        createTable(tableName: "rs_protocol_message") {
            column(name: "pm_id", type: "VARCHAR(36)") {
                constraints(nullable: "false")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "pm_transition", type: "VARCHAR(36)")

            column(name: "pm_message_content", type: "VARCHAR(255)")

            column(name: "pm_owner_id", type: "VARCHAR(36)") {
                constraints(nullable: "false")
            }

            column(name: "pm_valid", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "pm_message_timestamp", type: "timestamp") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "ibbo (generated)", id: "1520699228049-7") {
        createTable(tableName: "rs_protocol_request") {
            column(name: "pr_id", type: "VARCHAR(36)") {
                constraints(nullable: "false")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "pr_remote_symbol_service_fk", type: "VARCHAR(36)") {
                constraints(nullable: "false")
            }

            column(name: "pr_role", type: "VARCHAR(255)")

            column(name: "pr_rota_seq", type: "INT")

            column(name: "pr_owner_rsr_fk", type: "VARCHAR(36)") {
                constraints(nullable: "false")
            }

            column(name: "pr_current_state_fk", type: "VARCHAR(36)") {
                constraints(nullable: "false")
            }

            column(name: "pr_local_symbol_service_fk", type: "VARCHAR(36)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "ibbo (generated)", id: "1520699228049-8") {
        createTable(tableName: "rs_req") {
            column(name: "rsr_id", type: "VARCHAR(36)") {
                constraints(nullable: "false")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "rs_pagination", type: "VARCHAR(255)")

            column(name: "rs_publisher", type: "VARCHAR(255)")

            column(name: "rs_pubdate", type: "VARCHAR(255)")

            column(name: "rs_current_service_fk", type: "VARCHAR(36)")

            column(name: "rs_edition", type: "VARCHAR(255)")

            column(name: "rs_author_article", type: "VARCHAR(255)")

            column(name: "rs_isbn", type: "VARCHAR(255)")

            column(name: "rs_place_of_pub", type: "VARCHAR(255)")

            column(name: "rs_author", type: "VARCHAR(255)")

            column(name: "rs_issn", type: "VARCHAR(255)")

            column(name: "rs_volume", type: "VARCHAR(255)")

            column(name: "rs_nat_bib_no", type: "VARCHAR(255)")

            column(name: "rs_title", type: "VARCHAR(255)")

            column(name: "rs_add_no_let", type: "VARCHAR(255)")

            column(name: "rs_sub_title", type: "VARCHAR(255)")

            column(name: "rs_held_medium_type", type: "VARCHAR(255)")

            column(name: "rs_call_number", type: "VARCHAR(255)")

            column(name: "rs_title_article", type: "VARCHAR(255)")

            column(name: "rs_pubdate_component", type: "VARCHAR(255)")

            column(name: "rs_sysno", type: "VARCHAR(255)")

            column(name: "rs_sponsoring_body", type: "VARCHAR(255)")

            column(name: "rs_item_type", type: "VARCHAR(255)")

            column(name: "rs_patron_id", type: "VARCHAR(255)")

            column(name: "rs_issue", type: "VARCHAR(255)")

            column(name: "rs_series_title_no", type: "VARCHAR(255)")

            column(name: "rs_verif_src", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "ibbo (generated)", id: "1520699228049-9") {
        createTable(tableName: "rs_resource") {
            column(name: "rs_id", type: "VARCHAR(36)") {
                constraints(nullable: "false")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "rs_member_party_fk", type: "VARCHAR(36)") {
                constraints(nullable: "false")
            }

            column(name: "rs_owner_party_fk", type: "VARCHAR(36)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "ibbo (generated)", id: "1520699228049-10") {
        createTable(tableName: "rs_service") {
            column(name: "rss_id", type: "VARCHAR(36)") {
                constraints(nullable: "false")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "rss_symbol", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "ibbo (generated)", id: "1520699228049-11") {
        createTable(tableName: "rs_state") {
            column(name: "rss_id", type: "VARCHAR(36)") {
                constraints(nullable: "false")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "rss_code", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "rss_owner_sm_fk", type: "VARCHAR(36)") {
                constraints(nullable: "false")
            }

            column(name: "rss_descr", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "ibbo (generated)", id: "1520699228049-12") {
        createTable(tableName: "rs_state_model") {
            column(name: "rssm_id", type: "VARCHAR(36)") {
                constraints(nullable: "false")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "rssm_code", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "rssm_descr", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "ibbo (generated)", id: "1520699228049-13") {
        createTable(tableName: "rs_symbol") {
            column(name: "rss_id", type: "VARCHAR(36)") {
                constraints(nullable: "false")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "rss_authority_fk", type: "VARCHAR(36)") {
                constraints(nullable: "false")
            }

            column(name: "rss_owner_party_fk", type: "VARCHAR(36)") {
                constraints(nullable: "false")
            }

            column(name: "rss_symbol", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "ibbo (generated)", id: "1520699228049-14") {
        createTable(tableName: "rs_symbol_service") {
            column(name: "rsss_id", type: "VARCHAR(36)") {
                constraints(nullable: "false")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "rsss_service_fk", type: "VARCHAR(36)") {
                constraints(nullable: "false")
            }

            column(name: "rsss_symbol_fk", type: "VARCHAR(36)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "ibbo (generated)", id: "1520699228049-15") {
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

    changeSet(author: "ibbo (generated)", id: "1520699228049-16") {
        createTable(tableName: "rs_transition") {
            column(name: "rst_id", type: "VARCHAR(36)") {
                constraints(nullable: "false")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "rst_action_fk", type: "VARCHAR(36)") {
                constraints(nullable: "false")
            }

            column(name: "rst_from_state_fk", type: "VARCHAR(36)") {
                constraints(nullable: "false")
            }

            column(name: "rst_to_state_fk", type: "VARCHAR(36)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "ibbo (generated)", id: "1520699228049-17") {
        addPrimaryKey(columnNames: "rsc_id", constraintName: "rs_configPK", tableName: "rs_config")
    }

    changeSet(author: "ibbo (generated)", id: "1520699228049-18") {
        addPrimaryKey(columnNames: "rsna_id", constraintName: "rs_naming_authorityPK", tableName: "rs_naming_authority")
    }

    changeSet(author: "ibbo (generated)", id: "1520699228049-19") {
        addPrimaryKey(columnNames: "pty_id", constraintName: "rs_partyPK", tableName: "rs_party")
    }

    changeSet(author: "ibbo (generated)", id: "1520699228049-20") {
        addPrimaryKey(columnNames: "prot_id", constraintName: "rs_protocolPK", tableName: "rs_protocol")
    }

    changeSet(author: "ibbo (generated)", id: "1520699228049-21") {
        addPrimaryKey(columnNames: "pa_id", constraintName: "rs_protocol_actionPK", tableName: "rs_protocol_action")
    }

    changeSet(author: "ibbo (generated)", id: "1520699228049-22") {
        addPrimaryKey(columnNames: "pm_id", constraintName: "rs_protocol_messagePK", tableName: "rs_protocol_message")
    }

    changeSet(author: "ibbo (generated)", id: "1520699228049-23") {
        addPrimaryKey(columnNames: "pr_id", constraintName: "rs_protocol_requestPK", tableName: "rs_protocol_request")
    }

    changeSet(author: "ibbo (generated)", id: "1520699228049-24") {
        addPrimaryKey(columnNames: "rsr_id", constraintName: "rs_reqPK", tableName: "rs_req")
    }

    changeSet(author: "ibbo (generated)", id: "1520699228049-25") {
        addPrimaryKey(columnNames: "rs_id", constraintName: "rs_resourcePK", tableName: "rs_resource")
    }

    changeSet(author: "ibbo (generated)", id: "1520699228049-26") {
        addPrimaryKey(columnNames: "rss_id", constraintName: "rs_servicePK", tableName: "rs_service")
    }

    changeSet(author: "ibbo (generated)", id: "1520699228049-27") {
        addPrimaryKey(columnNames: "rss_id", constraintName: "rs_statePK", tableName: "rs_state")
    }

    changeSet(author: "ibbo (generated)", id: "1520699228049-28") {
        addPrimaryKey(columnNames: "rssm_id", constraintName: "rs_state_modelPK", tableName: "rs_state_model")
    }

    changeSet(author: "ibbo (generated)", id: "1520699228049-29") {
        addPrimaryKey(columnNames: "rss_id", constraintName: "rs_symbolPK", tableName: "rs_symbol")
    }

    changeSet(author: "ibbo (generated)", id: "1520699228049-30") {
        addPrimaryKey(columnNames: "rsss_id", constraintName: "rs_symbol_servicePK", tableName: "rs_symbol_service")
    }

    changeSet(author: "ibbo (generated)", id: "1520699228049-31") {
        addPrimaryKey(columnNames: "rstm_id", constraintName: "rs_tenant_mappingPK", tableName: "rs_tenant_mapping")
    }

    changeSet(author: "ibbo (generated)", id: "1520699228049-32") {
        addPrimaryKey(columnNames: "rst_id", constraintName: "rs_transitionPK", tableName: "rs_transition")
    }

    changeSet(author: "ibbo (generated)", id: "1520699228049-33") {
        addForeignKeyConstraint(baseColumnNames: "pm_transition", baseTableName: "rs_protocol_message", constraintName: "FK1ek1lnsqcr8hps2f56pdb8amv", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "rst_id", referencedTableName: "rs_transition")
    }

    changeSet(author: "ibbo (generated)", id: "1520699228049-34") {
        addForeignKeyConstraint(baseColumnNames: "rsss_service_fk", baseTableName: "rs_symbol_service", constraintName: "FK6tege05c8a3i6iptg73x1tywr", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "rss_id", referencedTableName: "rs_service")
    }

    changeSet(author: "ibbo (generated)", id: "1520699228049-35") {
        addForeignKeyConstraint(baseColumnNames: "pr_current_state_fk", baseTableName: "rs_protocol_request", constraintName: "FK9i86rvk3yu1o09eoa7af74ogy", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "rss_id", referencedTableName: "rs_state")
    }

    changeSet(author: "ibbo (generated)", id: "1520699228049-36") {
        addForeignKeyConstraint(baseColumnNames: "pr_remote_symbol_service_fk", baseTableName: "rs_protocol_request", constraintName: "FKaul0jagy02jgf5eco4tylvi8v", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "rsss_id", referencedTableName: "rs_symbol_service")
    }

    changeSet(author: "ibbo (generated)", id: "1520699228049-37") {
        addForeignKeyConstraint(baseColumnNames: "protocol_id", baseTableName: "rs_protocol_action", constraintName: "FKd3lnejtl0o6fx61h8msmmv390", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "prot_id", referencedTableName: "rs_protocol")
    }

    changeSet(author: "ibbo (generated)", id: "1520699228049-38") {
        addForeignKeyConstraint(baseColumnNames: "rs_owner_party_fk", baseTableName: "rs_resource", constraintName: "FKexa6dlfc1mglfkfj7hd0573xr", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "pty_id", referencedTableName: "rs_party")
    }

    changeSet(author: "ibbo (generated)", id: "1520699228049-39") {
        addForeignKeyConstraint(baseColumnNames: "rsss_symbol_fk", baseTableName: "rs_symbol_service", constraintName: "FKf7ax4vux8by823vs5qoexm414", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "rss_id", referencedTableName: "rs_symbol")
    }

    changeSet(author: "ibbo (generated)", id: "1520699228049-40") {
        addForeignKeyConstraint(baseColumnNames: "pm_owner_id", baseTableName: "rs_protocol_message", constraintName: "FKfitt40ue7qbxs10espuxuts37", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "pr_id", referencedTableName: "rs_protocol_request")
    }

    changeSet(author: "ibbo (generated)", id: "1520699228049-41") {
        addForeignKeyConstraint(baseColumnNames: "rst_to_state_fk", baseTableName: "rs_transition", constraintName: "FKhupf8tr1fv40u5119ddspwllj", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "rss_id", referencedTableName: "rs_state")
    }

    changeSet(author: "ibbo (generated)", id: "1520699228049-42") {
        addForeignKeyConstraint(baseColumnNames: "rst_from_state_fk", baseTableName: "rs_transition", constraintName: "FKj8xhsre67mek74tpw03e2tj5w", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "rss_id", referencedTableName: "rs_state")
    }

    changeSet(author: "ibbo (generated)", id: "1520699228049-43") {
        addForeignKeyConstraint(baseColumnNames: "rss_owner_party_fk", baseTableName: "rs_symbol", constraintName: "FKjxppu1o4fq3q1594oak2okhpl", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "pty_id", referencedTableName: "rs_party")
    }

    changeSet(author: "ibbo (generated)", id: "1520699228049-44") {
        addForeignKeyConstraint(baseColumnNames: "rst_action_fk", baseTableName: "rs_transition", constraintName: "FKn3ff08kx4nxd0blbdjiktoma0", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "pa_id", referencedTableName: "rs_protocol_action")
    }

    changeSet(author: "ibbo (generated)", id: "1520699228049-45") {
        addForeignKeyConstraint(baseColumnNames: "pr_local_symbol_service_fk", baseTableName: "rs_protocol_request", constraintName: "FKnjoh3ibmbdos1ks18124avqd2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "rsss_id", referencedTableName: "rs_symbol_service")
    }

    changeSet(author: "ibbo (generated)", id: "1520699228049-46") {
        addForeignKeyConstraint(baseColumnNames: "rs_current_service_fk", baseTableName: "rs_req", constraintName: "FKo11f06457f9yao6oqkh5nxd8c", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "pr_id", referencedTableName: "rs_protocol_request")
    }

    changeSet(author: "ibbo (generated)", id: "1520699228049-47") {
        addForeignKeyConstraint(baseColumnNames: "pr_owner_rsr_fk", baseTableName: "rs_protocol_request", constraintName: "FKp69exjmgs7hl8r7llgudan0oh", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "rsr_id", referencedTableName: "rs_req")
    }

    changeSet(author: "ibbo (generated)", id: "1520699228049-48") {
        addForeignKeyConstraint(baseColumnNames: "rss_authority_fk", baseTableName: "rs_symbol", constraintName: "FKrt406da8yj8yrjf1dgs6r2eof", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "rsna_id", referencedTableName: "rs_naming_authority")
    }

    changeSet(author: "ibbo (generated)", id: "1520699228049-49") {
        addForeignKeyConstraint(baseColumnNames: "rs_member_party_fk", baseTableName: "rs_resource", constraintName: "FKtumur1q4n2fg8pr0crpbwb4v", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "pty_id", referencedTableName: "rs_party")
    }

    changeSet(author: "ibbo (generated)", id: "1520699228049-50") {
        addForeignKeyConstraint(baseColumnNames: "rss_owner_sm_fk", baseTableName: "rs_state", constraintName: "FKy7pbh2tortdjy0cyr8rut7d1", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "rssm_id", referencedTableName: "rs_state_model")
    }
}

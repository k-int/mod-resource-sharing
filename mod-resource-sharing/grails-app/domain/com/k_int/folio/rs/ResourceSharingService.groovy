package com.k_int.folio.rs
import grails.gorm.MultiTenant


/**
 *  A ResourceSharingService is a record describing a protocol endpoint for messaging. A service
 *  can support multiple symbols. Examples are
 *      type | address
 *       tcp | some.host:1234
 *     email | ill.user@servicehost.com
 *
 */

class ResourceSharingService implements MultiTenant<ResourceSharingService> {

  String id
  String type
  String address

  static constraints = {
    type(nullable:false, blank:false)
    address(nullable:false, blank:false)
  }

  static mapping = {
    table 'rs_service'
    id(column:'rss_id', generator: 'uuid', length:36)
       type column:'rss_type'
    address column:'rss_address'
  }

}

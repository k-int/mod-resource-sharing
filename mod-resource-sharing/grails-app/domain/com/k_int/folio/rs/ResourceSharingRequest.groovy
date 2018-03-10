package com.k_int.folio.rs


import grails.gorm.MultiTenant
import grails.plugin.springsecurity.SpringSecurityService


/**
 * A request made to the library from a patron or other system user that the library
 * source an item not currently available in inventory.
 * This class holds the details the patron has given to the library to help locate and request
 * said item.
 */
class ResourceSharingRequest implements MultiTenant<ResourceSharingRequest> {

  String id
  String itemType  // monograph, serial, other
  String heldMediumType 
  String callNumber
  String author
  String title
  String subTitle
  String sponsoringBody
  String placeOfPublication
  String publisher
  String seriesTitleNumber
  String volume
  String issue
  String edition
  String publicationDate
  String publicationDateOfComponent
  String authorOfArticle
  String titleOfArticle
  String pagination
  String nationalBibNo
  String isbn
  String issn
  String systemNo
  String additionalNoLetters
  String verificationReferenceSource

  String patronId

  ProtocolRequest currentServiceRequest

  // rota is a simple set, but will be ordered by the rotaSequence element to allow easy reordering
  static hasMany = [
    rota:ProtocolRequest
  ]

  static mappedBy = [
    rota:'owner'
  ]

  static constraints = {
    itemType(nullable:true, blank:false)
    heldMediumType (nullable:true, blank:false)
    callNumber(nullable:true, blank:false)
    author(nullable:true, blank:false)
    title(nullable:true, blank:false)
    subTitle(nullable:true, blank:false)
    sponsoringBody(nullable:true, blank:false)
    placeOfPublication(nullable:true, blank:false)
    publisher(nullable:true, blank:false)
    seriesTitleNumber(nullable:true, blank:false)
    volume(nullable:true, blank:false)
    issue(nullable:true, blank:false)
    edition(nullable:true, blank:false)
    publicationDate(nullable:true, blank:false)
    publicationDateOfComponent(nullable:true, blank:false)
    authorOfArticle(nullable:true, blank:false)
    titleOfArticle(nullable:true, blank:false)
    pagination(nullable:true, blank:false)
    nationalBibNo(nullable:true, blank:false)
    isbn(nullable:true, blank:false)
    issn(nullable:true, blank:false)
    systemNo(nullable:true, blank:false)
    additionalNoLetters(nullable:true, blank:false)
    verificationReferenceSource(nullable:true, blank:false)
    currentServiceRequest(nullable:true, blank:false)
    patronId(nullable:true, blank:false)
  }

  static mapping = {
    table 'rs_req'
    id(column:'rsr_id', generator: 'uuid', length:36)
    rota sort:'rotaSequence', order: 'asc', bindable: false
                       itemType column:'rs_item_type'
                 heldMediumType column:'rs_held_medium_type'
                     callNumber column:'rs_call_number'
                         author column:'rs_author'
                          title column:'rs_title'
                       subTitle column:'rs_sub_title'
                 sponsoringBody column:'rs_sponsoring_body'
             placeOfPublication column:'rs_place_of_pub'
                      publisher column:'rs_publisher'
              seriesTitleNumber column:'rs_series_title_no'
                         volume column:'rs_volume'
                          issue column:'rs_issue'
                        edition column:'rs_edition'
                publicationDate column:'rs_pubdate'
     publicationDateOfComponent column:'rs_pubdate_component'
                authorOfArticle column:'rs_author_article'
                 titleOfArticle column:'rs_title_article'
                     pagination column:'rs_pagination'
                  nationalBibNo column:'rs_nat_bib_no'
                           isbn column:'rs_isbn'
                           issn column:'rs_issn'
                       systemNo column:'rs_sysno'
            additionalNoLetters column:'rs_add_no_let'
    verificationReferenceSource column:'rs_verif_src'

          currentServiceRequest column:'rs_current_service_fk'
                       patronId column:'rs_patron_id'
  }

}

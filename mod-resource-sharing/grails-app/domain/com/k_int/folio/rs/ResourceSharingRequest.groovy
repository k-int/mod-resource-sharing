package com.k_int.folio.rs

/**
 * A request made to the library from a patron or other system user that the library
 * source an item not currently available in inventory.
 * This class holds the details the patron has given to the library to help locate and request
 * said item.
 */
class ResourceSharingRequest {

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

  // rota is a simple set, but will be orderd by the rotaSequence element to allow easy reordering
  static hasMany = [
    rota:ProtocolRequest
  ]

  static mappedBy = [
    rota:'request'
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
    patronId(nullable:false, blank:false)
  }
}

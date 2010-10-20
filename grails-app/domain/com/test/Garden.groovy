package com.test

class Garden {

  String name
  
  static hasMany = [ trees:Tree ]
  
  static constraints = {
  }
  
  static mapping = {
    //associatedCollection sort:'height', order:"desc"
    trees sort:'height', order:'desc'
  }
}

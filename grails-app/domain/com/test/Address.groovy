package com.test

class Address {

    static constraints = {
      country (nullable:false)
      town   (nullable:false)
    }
    String country
    String town
    //Person person
    
    static  belongsTo = [person:Person]
//    static  belongsTo = Person
    
    String toString(){
      "town[" + town + "], country[" + country + "]"
    }
}

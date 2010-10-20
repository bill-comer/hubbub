package com.test

class Person {

    static constraints = {
      name (nullable:false)
      address (nullable:true)
    }
    
    String name
    Address address
    
    String toString() {
      "name[" + name + "]. address[" + address + "]"
    }
    
    static mapping = {
      address lazy:false
    }
}

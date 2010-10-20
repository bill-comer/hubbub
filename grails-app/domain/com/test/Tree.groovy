package com.test

class Tree {

  String name
  Integer height 
  
  static constraints = {
    name (size:3..8, blank:false)
  }
  
  static mapping = {
    sort height:"desc"
  } 
  
  String toString() {
    "name[" + name + "], height[" + height + "]"
  }
}

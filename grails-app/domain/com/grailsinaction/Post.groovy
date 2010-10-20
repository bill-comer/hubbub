package com.grailsinaction

import java.util.Date;

class Post {
  

  String content
  Date dateCreated = new Date()
  
  static constraints = {
    content blank:false
  }
  
  static belongsTo = [ user:User ]

  static mapping = {
    sort dateCreated:"desc"
  }  
  
  static hasMany = [ tags:Tag ]
  
  String toString() {
    "content[" + content + "]" + ", dateCreated[" + dateCreated + "\n"
  }
}

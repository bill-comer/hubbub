package com.grailsinaction

import groovy.lang.Closure;

import org.springframework.aop.TrueClassFilter;


class User {

  String userId
  String password
  Date dateCreated
  
  Profile profile
  
  static constraints = {
    userId (size:3..20, blank:false, unique: true)
    password (size:6..8, blank:false,
      validator:{passwd, user -> return passwd != user.userId})
    profile nullable:true
  }

  static hasMany = [ posts:Post, tags:Tag, following:User ]
  
  static mapping = {
    profile lazy:false
    posts sort:'dateCreated', order:'desc'
  }
  
  String toString() {
    "userId[" + userId + "], password[" + password + "], dateCreated[" + dateCreated + "],\n" + " posts[" + posts.each {it} + "]"
  }
}

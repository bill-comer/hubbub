package com.grailsinaction


import java.util.Date;
import java.util.Calendar;

class UserRegistrationCommand {
  
  String userId
  String password
  String passwordRepeat
  
  byte[] photo
  String fullName
  String bio
  String homepage
  String email
  String timezone
  String country
  String jabberAddress
  
  static constraints = {
    userId (size:3..20, blank:false)
    
    password (size:6..8, blank:false,
      validator:{passwd, urc -> return passwd != urc.userId})

    passwordRepeat (nullable:false,
      validator:{passwd2, urc -> return passwd2 == urc.password})
    
    fullName (nullable:true)
    bio nullable:true, maxSize:1000
    homepage url:true, nullable:true
    email email:true, nullable:true
    photo nullable:true
    country nullable:true
    timezone nullable:true
    jabberAddress email:true, nullable:true
  }
}

class UserController {

  def scaffold = true
  
  def search = { }
  
  def results = {
    println "search[" + params.userId + "]"
    def users = User.findAllByUserIdLike("%${params.userId}%")
    
    println "users-" + users
    println "users not null-" + (users != null)
    
    return [ users:users, term:params.userId ]
  }
  
  def register = {
    def user = new User(params)
    if (params.userId == null) {
      return
    } else if (user.validate()) {
      user.save()
      flash.message = "Successfully created user[" + user.userId + "]"
      redirect(uri:'/user/list')
    } else {
      flash.message = "Error Registering user[" + user.userId + "]"
      return [user:user]
    }
  }
  
  def register2 = { UserRegistrationCommand urc ->
    println "params[" + params + "]\n p-userId[" + params.userId +  "]"
    if (params.userId == null) {
      return
    } else if (urc.hasErrors()) {
      return [user:urc]
    } else {
      def user = new User (urc.properties)
      user.profile  = new Profile(urc.properties)
      if (user.save()) {
        flash.message = "Welcome abroad, ${urc.fullName?:urc.userId}"
        redirect(uri:'/user/list')
      } else {
        //may be not unique id !!!!!
      return [user:user]
      }
    }
  }
  
  def profile = {
    println "upload params[" + params + "], params.id[" + params.id + "]"
    def user = User.findByUserId(params.id)
    if (user?.profile?.photo) {
      response.setContentLength(user?.profile?.photo.length)
      response.outputStream << user.profile.photo
    } else {
      response.sendError(404)
    }
  }
  
  def create = {
    redirect(action:'register62')
  }
  
  def register62 = {UserRegistrationCommand urc ->
    println "register62"
    Calendar then = Calendar.getInstance();
    then.set(Calendar.YEAR, 1970);
    Date thenDate = then.getTime()
    Date now = new Date()

    if (urc.hasErrors()) {
      println "urc:ooops[" + now.getYear() + "]"
      return [user:urc, nowYear:now.getYear() + 1900, then:thenDate]
    } else {
      def user = new User (urc.properties)
      user.profile  = new Profile(urc.properties)
      Date dob = params.dob
      println "DOB:" + dob
      
      if (user.save()) {
        flash.message = "Welcome abroad, ${urc.fullName?:urc.userId}"
        redirect(uri:'/user/list')
      } else {
        //may be not unique id !!!!!
      return [user:user, nowYear:now.getYear() + 1900, then:thenDate]
      }
    }
  }
  
  
  //def index = { }
}

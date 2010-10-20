package com.grailsinaction

import javax.xml.ws.Response;


class PhotoUploadCommand {
  byte[] photo
  String userId
}

class ImageController { 

  def upload = { PhotoUploadCommand puc ->
    println "upload params[" + params + "], params.id[" + params.id + "] puc.userId[" + puc.userId + "]"
    def user = User.findByUserId(puc.userId)
    user.profile.photo = puc.photo
    user.save()
    redirect(controller:'user',action:'profile', id:puc.userId)
    //redirect(uri:'/user/list')
  }
  
  
  def form = {
    // pass thru to upload form
    def list = User.list()
    println "found " + list.size() + " users"
    list.each {
      println it
    }
    [userList:User.list()]
  }
  
  def view = {
    // pass thru to view photo page
  }
  
  //  def index = { }
}

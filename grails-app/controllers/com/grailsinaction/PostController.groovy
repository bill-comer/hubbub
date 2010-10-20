package com.grailsinaction

class PostController {

  
  def scaffold = true
 
  def postService
  
  def timeline = {
    println "timeline[" + params+ "], id[" + params.id + "]"
    if (params.id) {
      def user = User.findByUserId(params.id)
      [user:user]
    }
  }
  
  def addPost = {
    try {
      def newPost = postService.createPost(params.id, params.content)
      flash.message = "Added new Post: ${newPost.content}"
    } catch (e) {
      flash.message = e.message
    }
    
    redirect(action:'timeline', id:params.id)
  }
  
  def index = {
    if (!params.id) {
      flash.message "No user in scope"
    }
    redirect(action:'timeline', params:params)
    
  }
}

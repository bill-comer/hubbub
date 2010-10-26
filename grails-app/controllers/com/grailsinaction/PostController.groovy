package com.grailsinaction

class PostController {

  
  def scaffold = true
 
  def postService
  
  def timeline = {
    println "timeline[" + params+ "], id[" + params.id + "]"
    if (params.id) {
      def user = User.findByUserId(params.id)
      
      
      //def allposts = user?.posts
      //def posts = allposts.toList()
      
      def posts = Post.list(max:params.max?:2, offset:params.offset?:0)
      //posts = Post.list()
      
      def postCount = Post.count()
      
      println "max:" + params.max + ""
      println "posts.size:" + posts.size() + ", postCount:" + postCount
      
      [user:user, postCount:postCount, posts:posts]
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
  
  def listall = {
    def user = User.findByUserId(params.id)
    
    def allposts = user?.posts
    def posts = allposts.asList()
    def postCount = posts.size()
    
    println "posts.size:" + posts.size() + ", postCount:" + postCount
    
    [posts, postCount]
 }
  
  /*def index = {
    if (!params.id) {
      flash.message "No user in scope"
    }
    redirect(action:'timeline', params:params)
    
  }*/
}

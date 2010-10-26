package com.grailsinaction

class PostController {

  
  def scaffold = true
 
  def postService
  
  def timeline = {
    println "timeline[" + params+ "]"
    if (params.id) {
      def user = User.findByUserId(params.id)
      
      
      def allposts = user?.posts as List
      
      def max=params.max?:(allposts.size()>2 ? 2: allposts.size())
      def offset=params.offset?:(0)
      
      offset = offset as int
      max = max as int
      
      def start = offset*max > allposts.size()-1 ? allposts.size()-1 : offset*max
      def end = ((offset*max)+max-1) > allposts.size()-1 ? allposts.size() -1 : ((offset*max)+max-1)
      
      def posts = allposts.size() > 0?allposts[(start)..(end)]:allposts
      
      def postCount = allposts.size()
      
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

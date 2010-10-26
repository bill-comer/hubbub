package com.grailsinaction

import grails.test.*

class PostIntegrationTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testFirstPost() {
      def user = new User(userId:'joe', password:'secret').save()
      
      def post1 = new Post(content: "first post..wahoo.")
      user.addToPosts(post1)
      
      assertEquals 1, user.posts.size()
      
      def foundUser = User.get(user.id)
      assertNotNull "failed to get the user", foundUser
      
      assertNotNull foundUser.posts
      assertEquals 1, foundUser.posts.size()
      
      foundUser.posts.each {
        println "Post:" + it.content
      }
      
      //def fPost1 = foundUser.posts[0]
     def fPosts = foundUser.posts.collect {it.content}
     assertEquals 1, fPosts.size()
     assertEquals post1.content, fPosts[0]
    }
    
    def testSortOrder() {
      def user = new User(userId:'joe', password:'secret').save()
      
      def post1 = new Post(content: "1")
      def post2 = new Post(content: "2")
      def post3 = new Post(content: "3")
      def post4 = new Post(content: "4")
      def post5 = new Post(content: "5")
      def post6 = new Post(content: "6")

      user.addToPosts(post1)
      user.addToPosts(post2)
      user.addToPosts(post3)
      user.addToPosts(post4)
      user.addToPosts(post5)
      user.addToPosts(post6)
      
      def date = new Date()
      //post3.dateCreated.minus(1)
      
      def foundUser = User.get(user.id)
      assertNotNull foundUser.posts
      assertEquals 6, foundUser.posts.size()
      
      println foundUser
      
      foundUser.posts.each {
        println "Post:" + it
      }
      
      //assert foundUser.posts instanceof java.util.ArrayList
      
      def listofposts = foundUser.posts as List
      def max=2
      def offset=2
      def sublist = listofposts.sort()[(offset*max)..((offset*max)+max-1)]
      
      def allPosts = foundUser.posts.asList()
      assertEquals 6, allPosts.size()
      
      //allPosts = foundUser.posts.asList()
      assertEquals 2, sublist.size()
      
      sublist.each {
        println "sublist Post:" + it.content
      }
      
      
      
      
      
    }
    
    
    
}

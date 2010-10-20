package com.grailsinaction


import grails.test.*

class PostServiceIntegrationTests extends GrailsUnitTestCase {
  
  def postService
  
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testValidPost() {
      def user = new User(userId:'joe', password:'secret').save()
      
      def post = postService.createPost('joe', "first post")
      assertNotNull post
      assertEquals "first post", post.content
    }
    
    void testInValidPostNoContent() {
      def user = new User(userId:'joe', password:'secret').save()
      try {
        def post = postService.createPost('joe', "")
        shouldFail("Exception should have been thrown")
      }
      catch (e) {
        assertEquals "Invalid or empty post for user[joe]", e.message
      }
    }
    
    void testInValidPostInvalidUser() {
      def user = new User(userId:'joe', password:'secret').save()
      try {
        def post = postService.createPost('invalidUser', "valid content")
        shouldFail("Exception should have been thrown")
      }
      catch (e) {
        assertEquals "Invalid user id[invalidUser]", e.message
      }
    }
    
    
}

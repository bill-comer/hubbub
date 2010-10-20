package com.grailsinaction

import grails.test.*

class UserTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSomething() {

    }
    
    void testUserWithProfile() {
      mockDomain(User)
      def user = new User(userId:'joe', password:'secret')
      
      def profile = new Profile(fullName:'Bill Comer')
      user.profile= profile
      
      user.validate()
    }
    
    void testUserAllOk() {
      mockDomain(User)
      def user = new User(userId:'joe', password:'secret')
      
      assertTrue user.validate()
      assertFalse user.hasErrors()
    }
    
    
    void testUserPwdSameAsuser() {
      mockDomain(User)
      def user = new User(userId:'secret', password:'secret')
      
      assertFalse user.validate()
      assertTrue user.hasErrors()
    }
    
    void testNullPassword() {
      mockDomain(User)
      def user = new User(userId:'joe', password:'')
      
      assertFalse user.validate()
      assertTrue user.hasErrors()
      
      def errors = user.errors
      
      assertNotNull errors
      assertNotNull errors.getFieldError("password")
      
      assertEquals "blank", errors.getFieldError("password").code
      assertEquals "", errors.getFieldError("password").rejectedValue
      
      assertNull errors.getFieldError("userId")
    }
}

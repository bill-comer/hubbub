package com.grailsinaction

import grails.test.*

class ProfileIntegrationTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }
    

    void testPCUsingBindData() {
      LinkedHashMap params = [fullName:'fred', bio:'a bio']
      def controller = new ProfileController()
      def profile = controller.testBindData(params)
      
      assertEquals 'fred', profile.fullName
      assertEquals 'a bio', profile.bio
    }    
    
    
    
    void testPCReadingMapWithBlacklist() {
      def controller = new ProfileController()
      def profile = new Profile(fullName:'fred', bio:'a bio')
      
      Map params = [fullName:'fred2', bio:'a bio2']
      def profile2 = controller.testReadingMapWithBlacklist(profile, params)
      assertEquals 'fred2', profile2.fullName
      assertEquals 'a bio', profile2.bio
    }
    
    void testPCReadingMapWithWhitelistAsExpected() {
      def controller = new ProfileController()
      def profile = new Profile(fullName:'fred', bio:'a bio')
      assertEquals 'fred', profile.fullName
      assertEquals 'a bio', profile.bio
      
      Map params = [fullName:'fred2', bio:'a bio2']
      
      def profile2 = controller.readingMapWithWhitelistAsExpected(profile, params)
      
      println "DEBUG profile2.filename[" + profile2.fullName + "]"
      println "DEBUG profile2.bio[" + profile2.bio + "]"
      
      assertEquals 'a bio', profile2.bio
      assertEquals 'fred2', profile2.fullName
    }
    
    
    void testPCReadingMapWithWhitelist() {
      def controller = new ProfileController()
      def profile = new Profile(fullName:'fred', bio:'a bio')
      assertEquals 'fred', profile.fullName
      assertEquals 'a bio', profile.bio
      
      Map params = [fullName:'fred2', bio:'a bio2']
      
      def profile2 = controller.readingMapWithWhitelist(profile, params)
      
      println "DEBUG profile2.filename[" + profile2.fullName + "]"
      println "DEBUG profile2.bio[" + profile2.bio + "]"
      
      assertEquals 'a bio', profile2.bio
      assertEquals 'fred2', profile2.fullName
    }
    
    void testReadingMap() {
      Map params = [fullName:'fred', bio:'a bio']
      def profile = new Profile(params)
      assertEquals 'fred', profile.fullName
      assertEquals 'a bio', profile.bio
    }
}

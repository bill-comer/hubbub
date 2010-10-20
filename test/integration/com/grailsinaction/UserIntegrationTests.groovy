package com.grailsinaction
import groovy.lang.Closure;

import java.awt.List;


import com.sun.org.apache.xpath.internal.FoundIndex;
import grails.test.*

class UserIntegrationTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testFirstSaveEver() {
      def user = new User(userId:'joe', password:'secret')
      assertNotNull user.save()
      assertNotNull user.id
      
      def foundUser = User.get(user.id)
      assertNotNull foundUser
      assertEquals 'joe', foundUser.userId
    }
    
    void testSaveAndUpdate() {
      def user = new User(userId:'joe', password:'secret')
      assertNotNull user.save()
      assertNotNull user.id
      
      def foundUser = User.get(user.id)
      assertNotNull foundUser
      assertEquals 'joe', foundUser.userId
      assertEquals 'secret', foundUser.password
      
      foundUser.password = 'changed'
      foundUser.save()
      
      def editedUser = User.get(user.id)
      assertEquals 'changed', editedUser.password
    }
    
    void testDeleteUser() {
      def user = new User(userId:'joe', password:'secret', homepage:'http://www.grailsinaction.com')
      assertNotNull user.save()
      assertNotNull user.id
      
      def foundUser = User.get(user.id)
      assertNotNull foundUser
      assertEquals 'joe', foundUser.userId
      
      assertEquals 1, User.getAll().size()
      
      foundUser.delete()
      
      assertFalse User.exists(foundUser.id)
      assertEquals 0, User.getAll().size()
    }
    
    void testEvilSave() {
      def user = new User(userId:'joe', password:'tiny')
      
      assertFalse user.validate()
      assertTrue user.hasErrors()
      
      def errors = user.errors
      
      assertEquals "size.toosmall", errors.getFieldError("password").code
      assertEquals "tiny", errors.getFieldError("password").rejectedValue
      
      assertNull errors.getFieldError("userId")
    }
    
    void testNullPassword() {
      def user = new User(userId:'joe', password:'')
      
      assertFalse user.validate()
      assertTrue user.hasErrors()
      
      def errors = user.errors
      
      assertNotNull errors
      assertNotNull errors.getFieldError("password")
      
      assertEquals "size.toosmall", errors.getFieldError("password").code
      assertEquals "tiny", errors.getFieldError("password").rejectedValue
      
      assertNull errors.getFieldError("userId")
    }
    
    void testEvilSaveCorrected() {
      def user = new User(userId:'joe', password:'tiny')
      
      assertFalse user.validate()
      assertTrue user.hasErrors()
      
      def errors = user.errors
      
      assertEquals "size.toosmall", errors.getFieldError("password").code
      assertEquals "tiny", errors.getFieldError("password").rejectedValue
      
      
      assertNull errors.getFieldError("userId")
      
      user.password = "eightch"
      
      assertTrue user.validate()
      assertFalse user.hasErrors()
      
      assertNotNull user.save()
    }
    
    void testUserIdSameAsPassword() {
      def user = new User(userId:'bigenuff', password:'bigenuff')
      
      assertFalse user.validate()
      assertTrue user.hasErrors()
    }
    
    void testUserWithProfile() {
      def user = new User(userId:'joe', password:'secret')
      
      def profile = new Profile(fullName:'Bill Comer')
      user.profile= profile
      
      if (!user.validate())
      {
        user.errors.allErrors.each {
          println it
        }
      }
      
      assertTrue "should be a valid user", user.validate()
      assertFalse "should be no errors", user.hasErrors()

      assertNotNull "failed to save user", user.save()
      
      println "user id = [" + user.id + "]"
      
      def foundUser = User.get(user.id)
      assertNotNull "failed to get the user", foundUser
      assertEquals 'joe', foundUser.userId
      
      assertNotNull "failed to get the user for id[" + user.id + "]" , foundUser
      
      assertEquals "ids are not the same", user.id, foundUser.id
      assertNotNull "could not get the Profile", foundUser?.profile
      assertEquals "could not get the Profile fullName", 'Bill Comer', foundUser?.profile?.fullName
    }
    
    def testPostWithTags()
    {
      def user = new User(userId:'joe', password:'secret').save()
      
      def tagGroovy = new Tag(name:'groovy')
      def tagGrails = new Tag(name:'grails')
      user.addToTags(tagGroovy)
      user.addToTags(tagGrails)
      
      def tagNames = user.tags*.name
      assertEquals (['grails', 'groovy'], tagNames.sort())
      
      def groovyPost = new Post(content:'a groovy post')
      user.addToPosts(groovyPost)
      groovyPost.addToTags(tagGroovy)
      assertEquals 1, groovyPost.tags.size()
      
      def bothPost = new Post(content:'a groovy & grails post')
      user.addToPosts(bothPost)
      bothPost.addToTags(tagGroovy)
      bothPost.addToTags(tagGrails)
      assertEquals 2, bothPost.tags.size()
      
      assertEquals 1, User.count()
      assertEquals 2, Tag.count()
      assertEquals 2, Post.count()
      
      assertEquals 1, User.findAll().size()
      assertEquals 2, Tag.findAll().size()
      assertEquals 2, Post.findAll().size()
      
      user.delete()
      
      assertEquals 0, User.count()
      assertEquals 0, Tag.count()
      assertEquals 0, Post.count()
      
      assertEquals 0, User.findAll().size()
      assertEquals 0, Tag.findAll().size()
      assertEquals 0, Post.findAll().size()
    }
    
    def testFollowing() {
      def glen = new User(userId:'glen', password:'secret').save()
      def peter = new User(userId:'peter', password:'secret').save()
      def sven = new User(userId:'sven', password:'secret').save()
      
      glen.addToFollowing(peter)
      glen.addToFollowing(sven)
      assertEquals 2, glen.following.size()
      
      sven.addToFollowing(peter)
      assertEquals 1, sven.following.size()
    }
    
    def testLikeSearching() {
      def glen = new User(userId:'glen', password:'secret').save()
      def glen2 = new User(userId:'glen2', password:'secret').save()
      def sven = new User(userId:'sven', password:'secret').save()
      
      def user = User.findByUserId('glen')
      assertNotNull user
      assertEquals 'glen', user.userId
      
      def users = User.findAllByUserIdLike('glen%')
      assertNotNull users
      assertEquals 2, users.size()
      
      def usersSven = User.findAllByUserIdLike('sven')
      assertNotNull usersSven
      assertEquals 1, usersSven.size()
      
    }

    
    
    def testSaveClosure()
    {
      def user = new User(userId:'joe', password:'secret')
      //assertNotNull user.save()
      assertNotNull saveClosure(user)
      assertNotNull user.id
      
      def foundUser = User.get(user.id)
      assertNotNull foundUser
      assertEquals 'joe', foundUser.userId
    }
    
    def Closure saveClosure = { domainObj ->
      
      Object domainObject = domainObj.save()
      if(domainObject)
        println "Domain Object $domainObj Saved"
        else
        {
          println "Errors Found During Save of $domainObj!"
          println domainObj.errors.allErrors.each {
            println it.defaultMessage
          }
        }
        return domainObject
      }
    
    /*def testUserSaveClosure()
    {
      def user = new User(userId:'joe', password:'secret')
      //assertNotNull user.save()
      assertNotNull user.userSaveClosure(user)
      assertNotNull user.id
      
      def foundUser = User.get(user.id)
      assertNotNull foundUser
      assertEquals 'joe', foundUser.userId
    }*/
    
    
}

package com.test

import grails.test.*

class PersonIntegrationTestTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSomething() {
      def bill = new Person(name:'bill', address:new Address(town:'york', country:'UK')).save()
      def fred = new Person(name:'fred', address:new Address(town:'leeds', country:'UK')).save()
      def bjork = new Person(name:'helen', address:new Address(town:'helsinki', country:'finland')).save()
      def gustav = new Person(name:'john', address:new Address(town:'helsinki', country:'finland')).save()
      
      List ukAddresses = Address.findAllByCountry('UK') // find all by country
      
      println "num addresses-" + ukAddresses.size()
      
      for (int i in 0..<ukAddresses.size())
      {
        println "found person:" + ukAddresses[i].person
      }
      
      assertNotNull "bill can not ne null", bill
      assertTrue bill.validate() && !bill.hasErrors()
      assertTrue fred.validate() && !fred.hasErrors()
      assertTrue bjork.validate() && !bjork.hasErrors()
      assertTrue gustav.validate() && !gustav.hasErrors()
      
      assertEquals 2, ukAddresses.size()
    }
}

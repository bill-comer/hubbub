package com.test

import grails.test.*

class GardenIntegrationTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSomething() {
      def bills = new Garden(name:'bills').save()
      
      def oak = new Tree(name:'oak', height:1).save()
      def conker = new Tree(name:'conker', height:11).save()
      def lime = new Tree(name:'lime', height:111).save()
      
      bills.addToTrees(oak)
      bills.addToTrees(conker)
      bills.addToTrees(lime)
      
      assertEquals  3, bills.trees.size()
      
      //def results = Tree.list(sort:"title")
      List trees = bills.trees as List
      trees.each {
        println it
      }

      //assertEquals ('lime', trees[0].name)
      
      
      /*def treeNames = bills.trees*.name
      assertEquals ("sorted by name", ['conker', 'lime', 'oak'], treeNames.sort())
      
      def sizes = bills.trees*.size
      assertEquals ("sorted by size", [1, 11, 111], sizes.sort())
  */
      
    }
}

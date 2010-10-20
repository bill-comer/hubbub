package com.test

import grails.test.*

class TreeIntegrationTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSomething() {
      def oakTree = new Tree(name:'oak', height:1).save()
      def conker = new Tree(name:'conker', height:11).save()
      def lime = new Tree(name:'lime', height:111).save()

      def trees = Tree.findAll()
      
      assertEquals 3, trees.size()
      
      trees.each {
        println it
      }
      
      def treeSizes = trees*.height
      treeSizes.each {
        println it
      }
           
      def treeNames = trees*.name
      assertEquals (['conker', 'lime', 'oak'], treeNames.sort())
      
      
      def apple = new Tree(name:'apple', height:1).save()
      trees = Tree.findAll()
      treeNames = trees*.name
      assertEquals (['apple', 'conker', 'lime', 'oak'], treeNames.sort())  //sorted by name
      
      
    }
}

package com.test

import grails.test.*

class TreeTests extends GrailsUnitTestCase
{
  protected void setUp()
  {
    super.setUp()
  }
  
  protected void tearDown()
  {
    super.tearDown()
  }
  
  void testAllOk()
  {
    mockDomain(Tree)
    def tree = new Tree(name:'joe', height:123)
    
    assertTrue tree.validate()
    assertFalse tree.hasErrors()
  }
  
  void testNameTooShort()
  {
    mockDomain(Tree)
    def tree = new Tree(name:'jo', height:123)
    
    assertFalse tree.validate()
    assertTrue tree.hasErrors()
    
    def errors = tree.errors
    
    assertNotNull errors
    assertNotNull errors.getFieldError("name")
    assertNull errors.getFieldError("height")
  }
  
  
  void testNameEmpty()
  {
    mockDomain(Tree)
    def tree = new Tree(name:'', height:123)
    
    assertFalse tree.validate()
    assertTrue tree.hasErrors()
    
    def errors = tree.errors
    
    assertNotNull errors
    assertNotNull errors.getFieldError("name")
    assertNull errors.getFieldError("height")
  }
}

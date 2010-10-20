package com.test

import grails.test.GrailsUnitTestCase;
import grails.converters.*;

class JsonAdHocTests extends GrailsUnitTestCase
{
  
  void test1()
  {
    def url = new URL("http://minisculus.edendevelopment.co.uk/start")
    def response = JSON.parse(url.newReader()) // response is an instance of JSONObject (see Grails API docs)
    
    println response.toString(3) // Pretty-printed output
    response.each { key, value ->println "$key = $value" }
  }
}

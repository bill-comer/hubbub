package com.grailsinaction

class ProfileController {

  
  def scaffold = true
  
  def showPhoto = {
    println "upload params[" + params + "], params.id[" + params.id + "]"
    def profile = Profile.findById(params.id)
    if (profile?.photo) {
      response.setContentLength(profile?.photo.length)
      response.outputStream << profile.photo
    } else {
      response.sendError(404)
    }
  }
  
  //def index = { }
  
  // these methods are just testing bindata
  Profile testBindData(LinkedHashMap params) {
    def profile = new Profile()
    profile.fullName = "aaaaa"
    println "profile[" + profile + "]"
    println "params[" + params + "]"
    
    bindData(profile, params)
    return profile
  }
  
  Profile testReadingMapWithBlacklist(Profile profile, Map params) {
    bindData(profile, params, ['bio'])
    return profile
  }
  
  Profile readingMapWithWhitelistAsExpected(Profile profile, Map params) {
    profile.properties['fullName'] = params
    return profile
  }
  
  Profile readingMapWithWhitelist(Profile profile, Map params) {
    bindData(profile, params, profile.properties.collect{it.key} - ['fullName'])
    return profile
  }
  
  
  
  
}

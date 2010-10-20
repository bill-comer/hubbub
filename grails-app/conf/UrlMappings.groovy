class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
    
    "/users/$id" {
      controller = "post"
      action = "timeline"
    }

		"/"(view:"/index")
		"500"(view:'/error')
	}
}

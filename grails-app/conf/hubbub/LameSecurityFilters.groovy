package hubbub

class LameSecurityFilters {

    def filters = {
        all(controller:'*', action:'*') {
            before = {
                
            }
            after = {
                
            }
            afterView = {
              println "Finished running controller:${controllerName} action:${actionName}"
                
            }
        }
    }
    
}

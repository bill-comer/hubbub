
import com.grailsinaction.*;

class BootStrap {


    
    def init = { servletContext ->
      createFredUserIfRequired()
    }
    def destroy = {
    }
    
    void createFredUserIfRequired() {
      if (!User.findByUserId("fred")) {
        def aProfile= new Profile(fullName: 'Fred Bloggs',email:'fred@fred.com', bio: 'the best spinach uou have ever had is tinned spinach').save()
        new User(userId:'fred', password:'test1234', profile:aProfile ).save()
        
      }
    }
}

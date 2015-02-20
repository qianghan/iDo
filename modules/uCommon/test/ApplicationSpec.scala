import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._
import play.api.test.Helpers._
import java.io.File

import  neo4j.services.uman.UserManagementService
import neo4j.services.Neo4JServiceProvider
import neo4j.models.uman.Person

/**
* Add your spec here.
* You can mock out a whole application including requests, plugins etc.
* For more information, consult the wiki.
* Using the shipping domain model cyp as the sample data
*/
@RunWith(classOf[JUnitRunner])
class ApplicationSpec extends Specification {


	val modulePath = new File("./modules/uCommon/")
	
	"Common Module" should {

		"send 404 on a bad request" in {
			running(FakeApplication(path = modulePath)) {
				route(FakeRequest(GET, "/boum")) must beNone        
			}
		}
    
	}
  
  "User Management Service " should {
    "find by email" in {
       val service = UserManagementService.self()       
       val person2  = service.findByEmail("qiang.han@sap.com")
       person2.firstName mustEqual("Qiang")
    }
   
     "count persons " in {
       val service =UserManagementService.self()        
       val count  = service.getNumberOfPerson()
       count mustEqual(10)
    }
     
      "find by First Name  " in {
       val service = UserManagementService.self()         
       val person  = service.findByFirstName("Qiang")
       person.size() mustEqual(1)
       person.get(0).email mustEqual("qiang.han@sap.com")
    }
      
       "save a person  " in {
       val service = UserManagementService.self()   
       val person = new Person("test@machool.com")
       person.firstName = "test"
       person.lastName = "last test"
       person.sex ="F"
       val person2  = service.savePerson(person)
       
       service.delPerson(person2)
       person2.email mustEqual("test@machool.com")

       
    }
    
  }
}

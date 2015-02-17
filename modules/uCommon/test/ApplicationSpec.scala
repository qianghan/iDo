import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._
import play.api.test.Helpers._
import java.io.File

import  neo4j.services.UserManagementService
import neo4j.services.Neo4JServiceProvider
import neo4j.models.Person

/**
* Add your spec here.
* You can mock out a whole application including requests, plugins etc.
* For more information, consult the wiki.
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
       val service = Neo4JServiceProvider.get().umanService       
       val person2  = service.findByEmail("qiang.han@machool.com")
       person2.firstName mustEqual("Qiang")
    }
   
     "count persons " in {
       val service = Neo4JServiceProvider.get().umanService       
       val count  = service.getNumberOfPerson()
       count mustEqual(4)
    }
     
      "find by First Name  " in {
       val service = Neo4JServiceProvider.get().umanService       
       val person  = service.findByFirstName("Qiang")
       person.size() mustEqual(1)
       person.get(0).email mustEqual("qiang.han@machool.com")
    }
      
       "save a person  " in {
       val service = Neo4JServiceProvider.get().umanService 
       val person = new Person("test@machool.com")
       person.firstName = "test"
       person.lastName = "last test"
       person.sex ="F"
       val person2  = service.savePerson(person)
      
       person2.email mustEqual("test@machool.com")
    }
    
  }
}

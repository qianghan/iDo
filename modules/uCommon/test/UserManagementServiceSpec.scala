import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._
import play.api.test.Helpers._
import java.io.File

import  neo4j.services.uman.UserManagementService
import neo4j.services.Neo4JServiceProvider
import neo4j.models.uman._
import models.uman._

/**
* Add your spec here.
* You can mock out a whole application including requests, plugins etc.
* For more information, consult the wiki.
* Using the shipping domain model cyp as the sample data
*/
@RunWith(classOf[JUnitRunner])
class UserManagementServiceSpec extends Specification {


	val modulePath = new File("./modules/uCommon/")
	
	"Common Module" should {

		"send 404 on a bad request" in {
			running(FakeApplication(path = modulePath)) {
				route(FakeRequest(GET, "/boum")) must beNone        
			}
		}
    
	}
  
  "User Management Simple Service " should {
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
       
     "signup successfully " in {
       val service = UserManagementService.self()   
       
       val acc: Account =  new Account("test.machool@gmail.com", "helloworld")
       
       val company:Company = new Company("Test Machool Ltd")
       company.companyId = 9
       company.address = new Address ("unit 8",
                                      "900 mainland str ",
                                      "",
                                      "Vancouver",
                                      "BC",
                                      "CA",
                                      "V6b 1a9")
       
       company.email = "hello@gmail.com"
       company.tel = "9999999999"
       company.fax = "999999999"
       
       val contact:Person = new Person("ceo@gmail.com")
       contact.firstName = "test"
       contact.lastName = "last test"
       contact.sex ="F"
       contact.age = 34
       
       val signup:SignUp = new SignUp (acc, contact, company)
       
       val usr = service.signUp(signup)
       
       usr.account mustEqual "test.machool@gmail.com"
       
       
       
    }
     
      "signup failed due to dupliate account name " in {
       val service = UserManagementService.self()   
       
       val acc: Account =  new Account("test.machool@gmail.com", "helloworld")
       
       val company:Company = new Company("Test Machool Ltd")
       company.companyId = 9
       company.address = new Address ("unit 8",
                                      "900 mainland str ",
                                      "",
                                      "Vancouver",
                                      "BC",
                                      "CA",
                                      "V6b 1a9")
       
       company.email = "hello@gmail.com"
       company.tel = "9999999999"
       company.fax = "999999999"
       
       val contact:Person = new Person("ceo@gmail.com")
       contact.firstName = "test"
       contact.lastName = "last test"
       contact.sex ="F"
       contact.age = 34
       
       val signup:SignUp = new SignUp (acc, contact, company)
       
       val usr = service.signUp(signup)
       
       usr.status.status mustEqual "400"
       
       
       
    }
     
     
    
  }
}

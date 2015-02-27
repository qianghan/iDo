import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._
import play.api.test.Helpers._
import java.io.File
import play.api.libs.json.Json

@RunWith(classOf[JUnitRunner])
class AccountAdminSpec extends Specification {
   
  val modulePath = new File("./modules/uMan/")
  
  "CRUD Account workflow" should {
    
    "create a new account for a service" in {
    
      "fake" mustEqual "ekaf"
    }
  }
  
  
  "Service Configuration workflow" should {
    "add a new service to the account" in {
      "fake" mustEqual "ekaf"
    }
  }
  
   "Billing  workflow" should {
     "account status detemins billing" in {
       "fake" mustEqual "ekaf"
     }
  }
}
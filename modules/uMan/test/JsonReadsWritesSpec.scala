
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import controllers.uman.Formats
import neo4j.models.uman._
import models.uman._
import play.api.libs.json.JsSuccess
import play.api.libs.json.JsValue
import play.api.libs.json.Json
import org.specs2.runner.JUnitRunner
import play.api.Logger
import play.api.libs.json.JsError
import java.util.HashSet



@RunWith(classOf[JUnitRunner])
class JsonReadsWritesSpec extends Specification {
      ///////////////////////////////////////////////////////////////////////////////////////////////////////
                                            // Person Json
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    "Person Reads and Writes" should {
      "Person simple writes" in {
        implicit val personWrites = Formats.personWrites
        
        val person:Person = new Person("Qiang", "Han", 37, "M", "6043519437", "qiang.han@machool.com")
        val json = Json.toJson(person)    
        (json \ "firstName").asOpt[String] mustEqual Option("Qiang")
        
      }
      
      "Person simple reads" in {
        implicit val personReads = Formats.personReads
      
        
        val json:JsValue = Json.parse("""
          {
            "firstName": "Qiang",
            "lastName": "Han",
            "email":"qiang.han@machool.com",
            "age":37,
            "sex":"M",
            "telephone":"6043519437"
          
          }
        
        """)
        
        val jsresult = json.validate[Person] 
        
        jsresult must beLike {case x:JsSuccess[Person]=>x.get.firstName === "Qiang"}
        
      }
      
      "Person Format" in {
        implicit val personFormat = Formats.personFormat
        val person:Person = new Person("Qiang", "Han", 37, "M", "6043519437", "qiang.han@machool.com")
        
        val json = Json.toJson(person)
        
        (json \ "firstName").asOpt[String] mustEqual Option("Qiang")
        
         val jsresult = json.validate[Person] 
        
        jsresult must beLike {case x:JsSuccess[Person]=>x.get.firstName === "Qiang"}
        
      }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
                                            // Account Json
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
   "Account Reads and Writes" should {
     "Account simple writes" in {
       implicit val accountWrites = Formats.accountWrites
        val role:Role = new Role(1, "root")
        val roles  = new java.util.HashSet[Role]
        roles.add(role)
        
        val acc:Account = new Account("qiang.han@machool.com", "helloworld", roles)
        
        
        val json = Json.toJson(acc)  
        
        (json \ "username").asOpt[String] mustEqual Option("qiang.han@machool.com")
     }
     
     "Account simple reads" in {
       implicit val accountReads = Formats.accountReads
      
        
        val json:JsValue = Json.parse("""
          {
            "username": "qiang.han@machool.com",
            "password": "Han",
            "roles": [ {   "roleId": 0,
                           "role":"root" 
                       } 
                     ]
          
          }
        
        """)
        
        val jsresult = json.validate[Account] 
       
        jsresult must beLike {case x:JsSuccess[Account]=>x.get.username === "qiang.han@machool.com"}
     }
     
     "Account Format" in {
       implicit val accountFormat = Formats.accountFormat
       
       val role:Role = new Role(1, "root")
       val roles  = new java.util.HashSet[Role]
       roles.add(role)
        
       val acc = new Account("qiang.han@machool.com", "helloworld", roles)
        
        
       val json = Json.toJson(acc)  
       
       println(">>>>>" + Json.prettyPrint(json))
       
       (json \ "username").asOpt[String] mustEqual Option("qiang.han@machool.com")
       
       val jsresult = json.validate[Account] 
       
       jsresult must beLike {case x:JsSuccess[Account]=>x.get.username === "qiang.han@machool.com"}
       
     }
   }
   
      ///////////////////////////////////////////////////////////////////////////////////////////////////////
                                            // Company Json
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
   "Company Reads and Writes" should {
     "Company simple writes" in {
       implicit val companyWrites = Formats.companyWrites
        val add:Address = new Address("Unit 6", " 910 mainland street", "", "vancouver", "BC", "Canada", "V6b1a9")
        
        val com = new Company (90, "My Company", "200-500", "6043519567","6043519567","mycom@mcom.com", add)
       
        val json = Json.toJson(com)  
        
        (json \ "name").asOpt[String] mustEqual Option("My Company")
     }
     
     "Company simple reads" in {
       implicit val companyReads = Formats.companyReads
      
        
        val json:JsValue = Json.parse("""
          {
            "companyId": 90,
            "name": "My Company",
            "no_employees": "200-500",
            "tel": "6043519567",
            "fax": "6043519567",
            "email":"mycom@mcom.com",
            "address": {
              "unit": "Unit 9",
              "line1": "910 mainland street",
              "line2":"",
              "city":"vancouver",
              "province":"BC",
              "country":"Canada",
              "postcode": "v6b1a9"
            }
          
          }
        
        """)
        
        val jsresult = json.validate[Company] 
       
        jsresult must beLike {case x:JsSuccess[Company]=>x.get.name === "My Company"}
     }

   }
   
   
      ///////////////////////////////////////////////////////////////////////////////////////////////////////
                                            // Signup Json
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    
    "SignUp Reads and Writes" should {
       "SignUp simple writes" in {
          implicit val signupWrites = Formats.signupWrites
          
          val role:Role = new Role(1, "root")
          val roles  = new java.util.HashSet[Role]
          roles.add(role)
        
          val acc:Account = new Account("qiang.han@machool.com", "helloworld", roles) 
          val person:Person = new Person("Qiang", "Han", 37, "M", "6043519437", "qiang.han@machool.com")
          
          val add:Address = new Address("Unit 6", " 910 mainland street", "", "vancouver", "BC", "Canada", "V6b1a9")
          val com = new Company (90, "My Company", "200-500", "6043519567","6043519567","mycom@mcom.com", add)
          
          val signup = new SignUp(acc, person, com)
          
          val json = Json.toJson(signup)  
          
          println ("sign up json >>>" + Json.prettyPrint(json))
        
          (json \ "company" \ "name").asOpt[String] mustEqual Option("My Company")
          
          
       }
       
       "SignUp simple reads" in {
           implicit val signupReads = Formats.signupReads
           
           val json:JsValue = Json.parse("""
                {
                  "account" : {
                    "username" : "qiang.han@machool.com",
                    "password" : "helloworld",
                    "roles" : [ {
                      "roleId" : 1,
                      "role" : "root"
                    } ]
                  },
                  "contact" : {
                    "firstName" : "Qiang",
                    "lastName" : "Han",
                    "age" : 37,
                    "sex" : "M",
                    "telephone" : "6043519437",
                    "email" : "qiang.han@machool.com"
                  },
                  "company" : {
                    "companyId" : 90,
                    "name" : "My Company",
                    "no_employees" : "200-500",
                    "tel" : "6043519567",
                    "fax" : "6043519567",
                    "email" : "mycom@mcom.com",
                    "address" : {
                      "unit" : "Unit 6",
                      "line1" : " 910 mainland street",
                      "line2" : "",
                      "city" : "vancouver",
                      "province" : "BC",
                      "country" : "Canada",
                      "postcode" : "V6b1a9"
                    }
                  }
                }
           """)
       
          val jsresult = json.validate[SignUp] 
       
          jsresult must beLike {case x:JsSuccess[SignUp]=>x.get.company.name === "My Company"}
       }
       
       
    }
    
    
      ///////////////////////////////////////////////////////////////////////////////////////////////////////
                                            // User Json
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    
    "User Writes" should {
      "User simple writes" in {
        implicit val userWrites = Formats.userWrites
        
        val user = new User ("qiang.han@machool.com")
        val role:Role = new Role(1, "root")
        val roles  = new java.util.HashSet[Role]
        roles.add(role)
        user.roles = roles
        
        user.authorizedServices = new HashSet()
        user.authorizedServices.add("uShip")
        user.authorizedServices.add("uMan")
        user.authToken = "TOKEN_TEST"
        
        user.status = new StatusCode(500, "error", "testing", "I am wrong", "in user writes unit testing" )
        val json = Json.toJson(user)  
          
        println ("user json >>>" + Json.prettyPrint(json))
        
        (json \ "account" ).asOpt[String] mustEqual Option("qiang.han@machool.com")
        (json \ "status" \"error" ).asOpt[String] mustEqual Option("error")
      }
    }
    
    
}
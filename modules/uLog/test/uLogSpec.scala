package test

import org.junit.runner._
import org.specs2.mutable._
import org.specs2.runner._

import play.api.test._
import play.api.test.Helpers._
import java.io.File
import play.api.libs.json._
import play.api.mvc._
import scala.concurrent.Future

/**
* Add your spec here.
* You can mock out a whole application including requests, plugins etc.
* For more information, consult the wiki.
*/
@RunWith(classOf[JUnitRunner])
object uLogSpec extends PlaySpecification with Results {

  val modulePath = new File("./modules/uLog/")

  "uLog Module" should {

    "send 404 on a bad request" in {
      running(FakeApplication(path = modulePath)) {
        route(FakeRequest(GET, "/boum")) must beNone
      }
    }

    "save the log successfully when type is debug" in {
      running(FakeApplication(path = modulePath)){
        val request = FakeRequest(POST, "/logger").withJsonBody(Json.parse("""{
                                                                                  "url": "/uShipApp/components/uMan/home.html",
                                                                                  "message": "log type is debug.",
                                                                                  "type":"debug",
                                                                                  "account":"Machool",
                                                                                  "stackTrace":"",
                                                                                   "cause":""
                                                                             }"""))

        val logger = route(request).get
        status(logger) must equalTo(OK)
        contentType(logger) must beSome("application/json")
        contentAsString(logger) must contain("saved")
      }
    }

    "save the log successfully when type is info" in {
      running(FakeApplication(path = modulePath)){
        val request = FakeRequest(POST, "/logger").withJsonBody(Json.parse("""{
                                                                                  "url": "/uShipApp/components/uMan/home.html",
                                                                                  "message": "log type is info",
                                                                                  "type":"info",
                                                                                  "account":"Machool",
                                                                                  "stackTrace":"",
                                                                                   "cause":""
                                                                             }"""))

        val logger = route(request).get
        status(logger) must equalTo(OK)
        contentType(logger) must beSome("application/json")
        contentAsString(logger) must contain("saved")
      }
    }

    "save the log successfully when type is warn" in {
      running(FakeApplication(path = modulePath)){
        val request = FakeRequest(POST, "/logger").withJsonBody(Json.parse("""{
                                                                                  "url": "/uShipApp/components/uMan/home.html",
                                                                                  "message": "log type is warn",
                                                                                  "type":"warn",
                                                                                  "account":"Machool",
                                                                                  "stackTrace":"",
                                                                                   "cause":""
                                                                             }"""))

        val logger = route(request).get
        status(logger) must equalTo(OK)
        contentType(logger) must beSome("application/json")
        contentAsString(logger) must contain("saved")
      }
    }

    "save the log successfully when type is error" in {
      running(FakeApplication(path = modulePath)){
        val request = FakeRequest(POST, "/logger").withJsonBody(Json.parse("""{
                                                                                  "url": "/uShipApp/components/uMan/home.html",
                                                                                  "message": "log type is error",
                                                                                  "type":"error",
                                                                                  "account":"Machool",
                                                                                  "stackTrace":"",
                                                                                   "cause":""
                                                                             }"""))

        val logger = route(request).get
        status(logger) must equalTo(OK)
        contentType(logger) must beSome("application/json")
        contentAsString(logger) must contain("saved")
      }
    }

    "saving the log fail when log data is empty" in {
      running(FakeApplication(path = modulePath)){
        val request = FakeRequest(POST, "/logger")
        val logger = route(request).get
        status(logger) must equalTo(400)
      }
    }

    "saving the log failed when type is unknown" in {
      running(FakeApplication(path = modulePath)){
        val request = FakeRequest(POST, "/logger").withJsonBody(Json.parse("""{ 
                                                                                  "url": "/uShipApp/components/uMan/home.html",
                                                                                  "message": "log type is unknown",
                                                                                  "type":"unknown",
                                                                                  "account":"Machool",
                                                                                  "stackTrace":"",
                                                                                   "cause":""
                                                                             }"""))

        val logger = route(request).get
        status(logger) must equalTo(400)
        contentType(logger) must beSome("application/json")
        contentAsString(logger) must contain("unknown")
      }
    }

    "saving the log when type is exception" in {
      running(FakeApplication(path = modulePath)){
        val request = FakeRequest(POST, "/logger").withJsonBody(Json.parse("""{ 
                                                                                  "url": "/uShipApp/components/uMan/home.html",
                                                                                  "message": "log type is unknown",
                                                                                  "type":"exception",
                                                                                  "account":"Machool",
                                                                                  "stackTrace":"....",
                                                                                   "cause":"Exception Handling from Angular"
                                                                             }"""))

        val logger = route(request).get
        status(logger) must equalTo(200)
        contentType(logger) must beSome("application/json")
        contentAsString(logger) must contain("saved")
      }
    }

    "saving the log fails when log body is not complete (missing stackTrace)" in {
      running(FakeApplication(path = modulePath)){
        val request = FakeRequest(POST, "/logger").withJsonBody(Json.parse("""{ 
                                                                                  "url": "/uShipApp/components/uMan/home.html",
                                                                                  "message": "log type is unknown",
                                                                                  "type":"exception",
                                                                                  "account":"Machool",
                                                                                   "cause":"Exception Handling from Angular"
                                                                             }"""))

        val logger = route(request).get
        status(logger) must equalTo(400)
        contentType(logger) must beSome("application/json")
        contentAsString(logger) must contain("Error")
      }
    }

    "saving the log fails when log body is not complete (missing account)" in {
      running(FakeApplication(path = modulePath)){
        val request = FakeRequest(POST, "/logger").withJsonBody(Json.parse("""{ 
                                                                                  "url": "/uShipApp/components/uMan/home.html",
                                                                                  "message": "log type is unknown",
                                                                                  "type":"exception",
                                                                                  "stackTrace":"",
                                                                                   "cause":"Exception Handling from Angular"
                                                                             }"""))

        val logger = route(request).get
        status(logger) must equalTo(400)
        contentType(logger) must beSome("application/json")
        contentAsString(logger) must contain("Error")
      }
    }

    "saving the log fails when log body field misspelled (account-acc)" in {
      running(FakeApplication(path = modulePath)){
        val request = FakeRequest(POST, "/logger").withJsonBody(Json.parse("""{ 
                                                                                  "url": "/uShipApp/components/uMan/home.html",
                                                                                  "message": "log type is unknown",
                                                                                  "type":"exception",
                                                                                  "stackTrace":"",
                                                                                   "acc":"Machool",
                                                                                   "cause":"Exception Handling from Angular"
                                                                             }"""))

        val logger = route(request).get
        status(logger) must equalTo(400)
        contentType(logger) must beSome("application/json")
        contentAsString(logger) must contain("Error")
      }
    }
  }

}

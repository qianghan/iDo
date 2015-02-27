import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._
import play.api.test.Helpers._
import java.io.File
import play.api.libs.json.Json

/**
* Add your spec here.
* You can mock out a whole application including requests, plugins etc.
* For more information, consult the wiki.
*/
class ApplicationSpec extends Specification {

  val modulePath = new File("./modules/uMan/")

  "uMan Module" should {

    "send 404 on a bad request" in {
      running(FakeApplication(path = modulePath)) {
        route(FakeRequest(GET, "/boum")) must beNone
      }
    }

   /*

    // TODO: Refactor test scripts

    "create person" in {
      running(FakeApplication(path = modulePath)) {

        val jsonString = """{"firstName":"John","lastName":"Doe","age":50,"sex":"m","telephone":"1606954414","company":"Machool","email":"john.doe@machool.com"}"""
        //val jsonString = """{"firstName": "John", "lastName": "Doe"}"""
        val json = Json.parse(jsonString)

        val index = route(FakeRequest(POST, "/account", FakeHeaders(), json)).get

        status(index) must equalTo(CREATED)
        //contentType(index) must beSome.which(_ == "application/json")
        //contentAsString(index) must contain ("success")
      }
    }

    "retrieve person" in {
      running(FakeApplication(path = modulePath)) {

        val index = route(FakeRequest(GET, "/account/0")).get

        status(index) must equalTo(OK)
      }
    }

    "update person" in {
      running(FakeApplication(path = modulePath)) {

        val index = route(FakeRequest(PUT, "/account/0")).get

        status(index) must equalTo(ACCEPTED)
      }
    }

    "delete person" in {
      running(FakeApplication(path = modulePath)) {

        val index = route(FakeRequest(DELETE, "/account/0")).get

        status(index) must equalTo(NO_CONTENT)
      }
    }*/
  }
}

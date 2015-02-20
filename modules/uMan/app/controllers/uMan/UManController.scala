package controllers.uman

import play.api._
import play.api.mvc._
import play.api.libs.json._
import play.api.libs.functional.syntax._

import controllers.uCommon._
import neo4j.services._
import neo4j.models._

import com.google.gson.Gson

trait UManController extends Controller {

  implicit val personReads = new Reads[Person] {
    override def reads(js: JsValue): JsResult[Person] = JsSuccess(
      new Person (
        (js \ "firstName").as[String],
        (js \ "lastName").as[String],
        (js \ "age").as[Int],
        (js \ "sex").as[String],
        (js \ "telephone").as[String],
        (js \ "company").as[String],
        (js \ "email").as[String]
      )
    )
  }

  implicit val personWrites = new Writes[Person] {
      override def writes(person: Person): JsValue = {
        Json.obj(
          //"id" -> person.id,
          "firstName" -> person.getFirstName(),
          "lastName" -> person.getLastName(),
          "age" -> person.getAge(),
          "sex" -> person.getSex(),
          "telephone" -> person.getTelephone(),
          "company" -> person.getCompany().getName(),
          "email" -> person.getEmail()
        )
      }
  }

  // TODO: Refactor utility methods

  def retrieveAllPersons = Neo4jTransactionAction {

    def userManagementService: UserManagementService = Neo4JServiceProvider.get().userManagementService;
    def allPersons: java.util.List[Person] = userManagementService.getAllPersons()

    Ok(Json.toJson(new Gson().toJson(allPersons)))
  }

  def createPerson = Action(parse.json) { request =>
    val person = request.body.validate[Person]
    Created
  }

  def retrievePersonById(id: Long) = Action {
    Ok
  }

  def updatePerson(id: Long) = Action {
    Accepted
  }

  def updatePersonStatus(id: Long) = Action {
    Accepted
  }

  def deletePerson(id: Long) = Action {
    NoContent
  }

  def authenticatePerson(id: Long) = Action {
    NoContent
  }

}

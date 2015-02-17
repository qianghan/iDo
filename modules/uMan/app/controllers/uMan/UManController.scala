package controllers.uMan

import play.api._
import play.api.mvc._
import play.api.libs.json._
import play.api.libs.functional.syntax._
import neo4j.models._

trait UManController extends Controller {

  implicit val accountReads = new Reads[Account] {
    override def reads(js: JsValue): JsResult[Account] = {
      JsSuccess(
      Account(
        (js \ "id").as[Long],
        (js \ "firstName").as[String],
        (js \ "lastName").as[String],
        (js \ "age").as[Int],
        (js \ "sex").as[String],
        (js \ "telephone").as[String],
        (js \ "company").as[String]
      )
      )
    }
  }

  implicit val accountWrites = new Writes[Account] {
      override def writes(account: Account): JsValue = {
        Json.obj(
          "id" -> account.id,
          "firstName" -> account.firstName,
          "lastName" -> account.lastName,
          "age" -> account.age,
          "sex" -> account.sex,
          "telephone" -> account.telephone,
          "company" -> account.company
        )
      }
  }

  def retrieveAllAccounts = Action { implicit request =>
    Ok(Json.toJson(Account.list))
  }

  def createAccount = Action(parse.json) { request =>
    Ok(Json.obj("status"->"success"))
  }

}

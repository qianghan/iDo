package controllers.uMan

import models._
import play.api._
import play.api.mvc._
import play.api.Play.current
import play.api.libs.json.Json
import scala.language.postfixOps
import scala.collection.mutable.ListBuffer

object Application extends Controller {

  /**
  1. CRUD - account
  2. CRUD - uShip settings
  3. CR - bill
  */

  def index = Action { implicit request =>
    Ok(Json.obj("status" -> "success", "message"->"Hello! I'm uMan!"))
  }

  def retrieveAccount(id: Int) = Action { implicit request =>
     Ok(Json.toJson(accounts(id)))
  }

  def retrieveAccounts = Action { implicit request =>
    Ok(Json.toJson(accounts))
  }

  def updateAccount = Action { implicit request =>
    Ok(Json.toJson("true"))
  }

  def createAccount = Action { request =>
    Ok(Json.obj("status"->"success"))
  }

  def deleteAccount(id: Int) = Action { implicit request =>
    accounts -= accounts(id)
    Ok(Json.obj("status"->"success"))
  }

  def authenticateAccount(id: Int) = Action { implicit request =>
    Ok(Json.obj("status"->"success"))
  }

  var accounts = new ListBuffer[String]()
  accounts += """{ "id": "1", "firstName": "Eson", "lastName": "Paguia", "username": "eson.paguia@machool.com", “password": "1234", "companyId": "1", "accountStatusId": "1" }"""
  accounts += """{ "id": "2", "firstName": "Kamyar", "lastName": "Asadibeiky", "username": "kamyar.asadibeiky@machool.com", “password": "asdfg", "companyId": "1", "accountStatusId": "1" }"""
  accounts += """{ "id": "3", "firstName": "Qiang", "lastName": "Han", "username": "qianghan@machool.com", “password": "qwerty", "companyId": "1", "accountStatusId": "1" }"""

}

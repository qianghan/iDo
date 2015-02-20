package controllers.uman


import play.api._
import play.api.mvc._
import play.api.mvc.Results._
import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.libs.json.Reads._ 

import models.uman._


object Application extends Controller {
     implicit val AccountReads: Reads[Account] = (
      (JsPath \ "username").read[String] and  
      (JsPath \ "password").read[String] 
   )(Account.apply _)
   
  def createAccount = Action(BodyParsers.parse.json) { request =>
    val acc = request.body.validate[Account]
    Logger.debug(acc.toString())
     Ok(Json.toJson("true"))
  }

  def retrieveAccount = Action { implicit request =>
    Ok(Json.toJson("true"))
  }

  def updateAccount = Action { implicit request =>
    Ok(Json.toJson("true"))
  }

  def deleteAccount = Action { implicit request =>
    Ok(Json.toJson("true"))
  }
  
  /**
   * signUp takes input of signUp json { account, contact, company}
   * check repo to see if exists, if not save it and return success, with default authorized services, role
   * otherwise, error will be returned
   */
  
  def signUp = Action { request =>
    
    Ok(Json.toJson("true"))
  }

  /**
  1. CRUD - account
  2. CRUD - uShip settings
  3. CR - bill
  */

}



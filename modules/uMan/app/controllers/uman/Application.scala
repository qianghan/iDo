package controllers.uman


import play.api._
import play.api.mvc._
import play.api.Play.current
import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.libs.json.Reads._ 

import neo4j.models.uman.Account


object Application extends Controller {
 
   
  def createAccount = Action { request =>
    request.body.asJson.map {
      json => 
       
       Ok(Json.toJson("I works"))
    }.getOrElse {
      BadRequest("Incorrect json data")
    }
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


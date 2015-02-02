package controllers.uMan

import models._
import play.api._
import play.api.mvc._
import play.api.Play.current
import play.api.libs.json.Json

object Application extends Controller {

	def index = Action { implicit request =>
		Ok(views.html.uMan.index("Hello! I'm uMan!"))
	}

  def createAccount = Action { request =>
    request.body.asJson.map {
      json => 
        val a = json.as[Account]
        println(a)
        Ok(Json.toJson((a)))
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
  1. CRUD - account
  2. CRUD - uShip settings
  3. CR - bill
  */

}

package controllers.uship

import models._
import play.api._
import play.api.mvc._
import play.api.Play.current
import play.api.libs.json.Json

object Application extends Controller {

	def index = Action { implicit request =>
		Ok(Json.obj("code" -> "200", "message"->"Hello! I'm uShip!"))
	}

}

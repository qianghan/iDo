package controllers.uman

import play.api._
import play.api.mvc._
import play.api.libs.json.Json

object Application extends UManController {

  def index = Action {
    Ok(Json.obj("status" -> "success", "message"->"Hello! I'm uMan!"))
  }

}
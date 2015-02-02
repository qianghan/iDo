package controllers.uMan

import models._
import play.api._
import play.api.mvc._
import play.api.Play.current

object Application extends Controller {

	def index = Action { implicit request =>
		Ok(views.html.uMan.index("Hello! I'm uMan!"))
	}


  /**
  1. CRUD - account
  2. CRUD - uShip settings
  3. CR - bill
  */

  
}

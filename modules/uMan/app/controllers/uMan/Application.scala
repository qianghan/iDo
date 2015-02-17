package controllers.uMan

import play.api._
import play.api.mvc._
import play.api.libs.json.Json
//import services.account.AccountServiceComponentImpl
//import repositories.account.AccountRepositoryComponentImpl

object Application extends UManController {
//                    with AccountServiceComponentImpl 
//                    with AccountRepositoryComponentImpl {

  def index = Action {
    Ok(Json.obj("status" -> "success", "message"->"Hello! I'm uMan!"))
  }

}

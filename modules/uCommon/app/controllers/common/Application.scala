package controllers.uCommon

import play.api._
import play.api.mvc._
import play.api.libs.json.Json

import services._
import models._
import org.springframework.beans.factory.annotation.Autowired

@org.springframework.stereotype.Controller
class Application @Autowired() (accountService: AccountService) extends Controller {

	def status = Action {

    def allAccounts: java.util.List[Account] = accountService.getAllAccounts()

    Ok(Json.obj("status" -> "success", "message"->"Hello! I'm uCommon! %s".format(allAccounts.size())))
	}

}

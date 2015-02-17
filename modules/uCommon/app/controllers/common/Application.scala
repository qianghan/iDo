package controllers.uCommon

import play.api._
import play.api.mvc._
import play.api.libs.json.Json

import controllers._
import services._
import models._

object Application extends Controller {

	def status = Neo4jTransactionAction {

    def accountService: AccountService = Neo4JServiceProvider.get().accountService;

    def allAccounts: java.util.List[Account] = accountService.getAllAccounts()

    Ok(Json.obj("status" -> "success", "message"->"Hello! I'm uCommon! %s".format(allAccounts.size())))
	}

}

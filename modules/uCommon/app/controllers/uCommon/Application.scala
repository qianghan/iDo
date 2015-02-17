package controllers.uCommon

import play.api._
import play.api.mvc._
import play.api.libs.json.Json

import controllers._
import neo4j.services._
import neo4j.models._

object Application extends Controller {

	def status = Neo4jTransactionAction {

    def userManagementService: UserManagementService = Neo4JServiceProvider.get().userManagementService;

    def allPersons: java.util.List[Person] = userManagementService.getAllPersons()

    //userManagementService.findByEmail("eson.paguia@machool.com");

    Ok(Json.obj("status" -> "success", "message"->"Hello! I'm uCommon! %d".format(allPersons.size())))
  }

}

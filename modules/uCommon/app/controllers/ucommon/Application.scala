package controllers.ucommon

import play.api._
import play.api.mvc._
import play.api.libs.json.Json

import neo4j.services._
import neo4j.models.uman._
import neo4j.services.Neo4JServiceProvider
import neo4j.services.uman.UserManagementService

/**
* uCommon does not need a controller in most cases. 
* Other modules access uCommon using services.
* Controller and routes are kept for testing purpose, and should be removed later. 
*/
object Application extends Controller {

  def test = Neo4jTransactionAction {

    def userManagementService: UserManagementService = Neo4JServiceProvider.get().userManagementService;

    def allPersons: java.util.List[Person] = userManagementService.getAllPersons()

    Ok(Json.obj("status" -> "success", "message"->"Hello! I'm uCommon! %d".format(allPersons.size())))

  }

}

package controllers.uCommon;

import neo4j.models.Person;
import neo4j.services.UserManagementService;
import neo4j.services.Neo4JServiceProvider;
import play.mvc.Controller;
import play.mvc.Result;
import neo4j.plugins.Transactional;
import java.util.List;

public class ApplicationController extends Controller {

  @Transactional
  public static Result index() {

    final UserManagementService userManagementService = Neo4JServiceProvider.get().userManagementService;

    final List<Person> allPersons = userManagementService.getAllPersons();

    return ok("hello");
  }

}

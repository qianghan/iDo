package controllers.uman

import play.api._
import play.api.mvc._
import play.mvc.Http
import play.api.libs.json._
import play.api.libs.functional.syntax._


import neo4j.services._
import neo4j.models.uman._
import neo4j.models.uman.Account 
import neo4j.services.uman.UserManagementService
import scala.collection.JavaConverters._
import models.uman.SignUp
import models.uman.User
import models.uman.StatusCode
import play.api.mvc.Results._

import services.uman.impl._
import services.uman._
import services.ServiceFactory


object UManController extends Controller {
  
  val SERVICE_PROVIDER =  ServiceFactory.SERVICE_UMAN_NEO4J 
  val logger: Logger = Logger("uManController")

  implicit val signUpFormat = Formats.signupFormat  
  implicit val accountFormat = Formats.accountFormat
  implicit val userWrites = Formats.userWrites
   /**
    * SignUp Action
    */
  
  def signup = Action(BodyParsers.parse.json) {request =>
     val signUp = request.body.validate[SignUp]
     
     logger.info("Sign up Action is triggered ....")
     
     signUp.fold(
        errors=>{
          logger.error("Bad Request: " + errors)
          BadRequest(Json.obj("status" ->"Error", "message" -> JsError.toFlatJson(errors)))
        },
        value => {
          val service = AccountServicesFactory.apply(SERVICE_PROVIDER)
          logger.debug("Service provider is >>>" + SERVICE_PROVIDER)
          
          val user = service.signup(value)
          
          processUser(user)
        }
     )
  }
  
  def login = Action(BodyParsers.parse.json) {request =>
    val account = request.body.validate[Account]
    logger.info("Login up Action is triggered ....")
     
    account.fold(
        errors => {
          logger.error("Bad Request: " + errors)
          BadRequest(Json.obj("status" ->"Error", "message" -> JsError.toFlatJson(errors)))
        },
        value => {
          val service = AccountServicesFactory.apply(SERVICE_PROVIDER)
          logger.debug("Service provider is >>>" + SERVICE_PROVIDER)
          
          val user = service.login(value)
          processUser(user)
        }
    )
  }


  def processUser(user:User):Result = {
  //check user's status code
     val status = user.status.status
            
    val result = status match {
       case Http.Status.OK => Ok(Json.toJson(user))
       case Http.Status.CONFLICT => Conflict(Json.toJson(user))
       case Http.Status.FORBIDDEN => Forbidden(Json.toJson(user))
       case Http.Status.INTERNAL_SERVER_ERROR => InternalServerError(Json.toJson(user))
       case Http.Status.NOT_FOUND => NotFound(Json.toJson(user))
       case _ => BadRequest(Json.toJson(user))
              
    }
            
    logger.debug("process user result>>> " + Json.toJson(user).toString() )
            
    result
}
  // TODO: Refactor utility methods
/*
  def retrieveAllPersons = Action {

    def userManagementService: UserManagementService = UserManagementService.self();
    def allPersons: java.util.List[Person] = userManagementService.getAllPersons()

    Ok(Json.toJson(new Gson().toJson(allPersons)))
  }
  
 

  def createPerson = Action(parse.json) { request =>
    val person = request.body.validate[Person]
    Created
  }

  def retrievePersonById(id: Long) = Action {
    Ok
  }

  def updatePerson(id: Long) = Action {
    Accepted
  }

  def updatePersonStatus(id: Long) = Action {
    Accepted
  }

  def deletePerson(id: Long) = Action {
    NoContent
  }

  def authenticatePerson(id: Long) = Action {
    NoContent
  }
  * */
  
}

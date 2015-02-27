package services.uman

import models.uman._

import neo4j.services.uman._
import services.ServiceFactory
import neo4j.models.uman._

class uManServiceNeo4jImpl extends uManService{
  
  val service_type:String = ServiceFactory.SERVICE_UMAN_NEO4J
  
  override def serviceType ={service_type}
  
  override def signUp (signup:SignUp):User = {
    
    val uman = UserManagementService.self()
    uman.signUp(signup)
  }
  
  
   override def login(account: Account):User = {
     val uman = UserManagementService.self()
     uman.login(account)
   }
  
}
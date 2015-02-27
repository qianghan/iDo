package services.uman.impl

import services.uman._
import models.uman._
import neo4j.models.uman.Account
import services._

class AccountServicesNeo4jImpl extends AccountServices{

  override def signup(signUp: SignUp): User = {
    val service = ServiceFactory.apply(ServiceFactory.SERVICE_UMAN_NEO4J)
    
      service match {
      case srv:uManService => srv.signUp(signUp)
      case _ => throw new ClassCastException
    }
    
  }

  override def login(account: Account): User = {
    val service = ServiceFactory.apply(ServiceFactory.SERVICE_UMAN_NEO4J)
    
    service match {
      case srv:uManService => srv.login(account)
      case _ => throw new ClassCastException
    }

  }

}

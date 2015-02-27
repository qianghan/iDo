package services.uman

import services.uman.impl._
import services.ServiceFactory
import play.api.Logger

object AccountServicesFactory {
  
  val logger: Logger = Logger("AccountServicesFactory")
  
  def apply(srv:String):AccountServices ={
    
    srv match {
      case ServiceFactory.SERVICE_UMAN_NEO4J => new AccountServicesNeo4jImpl
      case _ => { 
                  logger.error("Null is returned. Service Type is not recognized >>>" + srv)
                  null
      }
    }
}
}
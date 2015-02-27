package services

import services.uman._

object ServiceFactory {
  
  val SERVICE_UMAN_NEO4J:String = "uMan.Neo4J"
  
  def apply(srv:String):Service ={
    
    if (srv == SERVICE_UMAN_NEO4J) 
      new uManServiceNeo4jImpl
    else null
  }

}
package controllers.uCommon

import play.api._
import play.api.mvc._
import neo4j.services._
object Application extends Controller {
  
	def test = Action {
    
    val service = Neo4JServiceProvider.get().umanService
                
    val person = service.findByFirstName("Qiang")
    
    System.out.println("first name >>" )
    
    System.out.println("first name >>" )
    
    System.out.println("first name >>" )
    
    val person2 = service.findByEmail("qiang.han@machool.com")
    
    System.out.println("first name >>" + person2.firstName)
    
		Ok("Everything is great!")
	}
  
}

import play.api._
import play.api.mvc._
import play.api.mvc.Results._
import scala.concurrent.Future
import org.springframework.context.support.ClassPathXmlApplicationContext
import org.springframework.data.neo4j.support.Neo4jTemplate

object Global extends GlobalSettings {

	/**
	* Define the application context to be used.
	*/
	val ctx = new ClassPathXmlApplicationContext("applicationContext.xml")

	/**
	* Sync the context lifecycle with Play's.
	* @param app
	*/
	override def onStart(app: Application) {
		ctx.start()
	}

	/**
	* Sync the context lifecycle with Play's.
	* @param app
	*/
	override def onStop(app: Application) {
		val neoTemplate:Neo4jTemplate = ctx.getBean(classOf[Neo4jTemplate]);
		neoTemplate.getGraphDatabaseService.shutdown();
		ctx.stop()
	}
	
	/**
	* Controllers must be resolved through the application context. There is a special method of GlobalSettings
	* that we can override to resolve a given controller. This resolution is required by the Play router.
	* @param controllerClass
	* @param A
	* @return
	*/
	override def getControllerInstance[A](controllerClass: Class[A]): A = ctx.getBean(controllerClass)
	
	private def getSubdomain (request: RequestHeader) = request.domain.replaceFirst("[\\.]?[^\\.]+[\\.][^\\.]+$", "")
	
	override def onRouteRequest (request: RequestHeader) = getSubdomain(request) match {
		case "uMan" => uMan.Routes.routes.lift(request)
    	case "uShip" => uShip.Routes.routes.lift(request)
    	case _ => uShip.Routes.routes.lift(request)
	}
	
	// 404 - page not found error
	override def onHandlerNotFound (request: RequestHeader) = getSubdomain(request) match {
		case "uMan" => GlobalUMan.onHandlerNotFound(request)
    	case "uShip" => GlobalUShip.onHandlerNotFound(request)
    	case _ => GlobalUShip.onHandlerNotFound(request)
	}
	
	// 500 - internal server error
	override def onError (request: RequestHeader, throwable: Throwable) = getSubdomain(request) match {
		case "uMan" => GlobalUMan.onError(request, throwable)
    	case "uShip" => GlobalUShip.onError(request, throwable)
    	case _ => GlobalUShip.onError(request, throwable)
	}
	
	// called when a route is found, but it was not possible to bind the request parameters
	override def onBadRequest (request: RequestHeader, error: String) = getSubdomain(request) match {
    	case "uMan" => GlobalUMan.onBadRequest(request, error)
    	case "uShip" => GlobalUShip.onBadRequest(request, error)
		case _ => GlobalUShip.onBadRequest(request, error)
	}

}

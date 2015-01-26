import play.api._
import play.api.mvc._
import play.api.mvc.Results._
import scala.concurrent.Future

object GlobalUMan extends GlobalSettings {	
	
	// 404 - page not found error
	override def onHandlerNotFound (request: RequestHeader) = Future.successful(
		NotFound(views.html.uMan.errors.onHandlerNotFound(request))
	)
	
	// 500 - internal server error
	override def onError (request: RequestHeader, throwable: Throwable) = Future.successful(
		InternalServerError(views.html.uMan.errors.onError(throwable))
	)
	
	// called when a route is found, but it was not possible to bind the request parameters
	override def onBadRequest (request: RequestHeader, error: String) = Future.successful(
		BadRequest("Bad Request: " + error)
	)

}

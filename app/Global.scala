import play.api._
import play.api.mvc._
import play.api.mvc.Results._
import scala.concurrent.Future

object Global extends GlobalSettings {

  private def getSubdomain (request: RequestHeader) = request.domain.replaceFirst("[\\.]?[^\\.]+[\\.][^\\.]+$", "")

  override def onRouteRequest (request: RequestHeader) = getSubdomain(request) match {
    case "uMan" => uMan.Routes.routes.lift(request)
    case "uShip" => uShip.Routes.routes.lift(request)
    case "uLog" => uLog.Routes.routes.lift(request)
    case _ => uShip.Routes.routes.lift(request)
  }

  // 404 - page not found error
  override def onHandlerNotFound (request: RequestHeader) = getSubdomain(request) match {
    case "uMan" => GlobalUMan.onHandlerNotFound(request)
    case "uShip" => GlobalUShip.onHandlerNotFound(request)
    case "uLog" => GlobalUShip.onHandlerNotFound(request)
    case _ => GlobalUShip.onHandlerNotFound(request)
  }

  // 500 - internal server error
  override def onError (request: RequestHeader, throwable: Throwable) = getSubdomain(request) match {
    case "uMan" => GlobalUMan.onError(request, throwable)
    case "uShip" => GlobalUShip.onError(request, throwable)
    case "uLog" => GlobalUShip.onError(request, throwable)
    case _ => GlobalUShip.onError(request, throwable)
  }

  // called when a route is found, but it was not possible to bind the request parameters
  override def onBadRequest (request: RequestHeader, error: String) = getSubdomain(request) match {
    case "uMan" => GlobalUMan.onBadRequest(request, error)
    case "uShip" => GlobalUShip.onBadRequest(request, error)
    case "uLog" => GlobalUShip.onBadRequest(request, error)
    case _ => GlobalUShip.onBadRequest(request, error)
  }

}

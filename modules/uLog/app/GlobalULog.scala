import play.api._
import play.api.mvc._
import play.api.mvc.Results._
import scala.concurrent.Future
import play.api.libs.json.Json

object GlobalULog extends GlobalSettings {	

  // 404 - page not found error
  override def onHandlerNotFound (request: RequestHeader) = Future.successful(
    NotFound(Json.obj("error" ->  "URL not found","code" -> "404", "message"->request.toString()))
  )

  // 500 - internal server error
  override def onError (request: RequestHeader, throwable: Throwable) = Future.successful(
    InternalServerError(Json.obj("error" ->  "Internal server error","code" -> "500", "message"->throwable.toString()))
  )

  // called when a route is found, but it was not possible to bind the request parameters
  override def onBadRequest (request: RequestHeader, error: String) = Future.successful(
    BadRequest("Bad Request: " + error)
  )

}

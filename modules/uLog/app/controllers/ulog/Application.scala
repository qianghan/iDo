package controllers.ulog

import play.api._
import play.api.mvc._
import play.api.mvc.Results._
import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.libs.json.Reads._ 
import services.ulog.LogServiceImpl
import models.log.LogBody

object Application extends Controller {
  
    implicit val LogBodyReads: Reads[LogBody] = (
      (JsPath \ "url").read[String] and  
      (JsPath \ "message").read[String] and 
      (JsPath \ "type").read[String] and
      (JsPath \ "account").read[String] and 
      (JsPath \ "stackTrace").read[String]  and 
      (JsPath \ "cause").read[String]
   )(LogBody.apply _)
 
  
	
	def saveLog = Action(BodyParsers.parse.json) {request =>
        val logbody = request.body.validate[LogBody]
        val logService = new LogServiceImpl()
        logService.saveLog(logbody)
  }

}

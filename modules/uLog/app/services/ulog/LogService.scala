package services.ulog

import models.log.LogBody
import play.api.libs.json._
import play.api.mvc._

trait LogService {
  
  def saveLog(logbody: JsResult[LogBody]):Result
  

}
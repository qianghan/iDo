package services

import play.api._
import play.api.mvc._
import play.api.mvc.Results._
import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.libs.json.Reads._ 
import play.api.Logger
import domain.log.LogBody

class LogServiceImpl extends LogService{

      
   //right now, it is hard coded with "uShipApp", and detail sources are in URL
   //better way is to have logbody has module name from client. But it might be overdo, as all logging on client has to pass 'module' name
   //module name can be derived from path though. 
  
  //When Authorization is added. Account should be replaced with the server side, and no need for client to provide. 
      
   override def saveLog(logbody: JsResult[LogBody]):Result = {
     
        val uShipLogger: Logger = Logger("uShipApp")
        
        logbody.fold(
          errors => {
            uShipLogger.error("Bad Request: " + errors)
            return BadRequest(Json.obj("status" ->"Error", "message" -> JsError.toFlatJson(errors)))
          },
          msg =>{
            
            if (msg.logType == "debug"){
              uShipLogger.debug("[source url]--" + msg.source)
              uShipLogger.debug("[message]--" + msg.msg)
              uShipLogger.debug("[account]--" + msg.account)
              
            }else if (msg.logType == "error"){
              uShipLogger.error("[source url]--" + msg.source)
              uShipLogger.error("[message]--" + msg.msg)
              uShipLogger.debug("[account]--" + msg.account)
              
            }else if (msg.logType == "info"){
              uShipLogger.info("[source url]--" + msg.source)
              uShipLogger.info("[message]--" + msg.msg)
              uShipLogger.debug("[account]--" + msg.account)
              
            }else if (msg.logType == "warn"){
              uShipLogger.warn("[source url]--" + msg.source)
              uShipLogger.warn("[message]--" + msg.msg)
              uShipLogger.debug("[account]--" + msg.account)
            }else if (msg.logType == "exception"){
              uShipLogger.error("[source url]--" + msg.source)
              uShipLogger.error("[message]--" + msg.msg)
              uShipLogger.error("[stacktrace]--" + msg.stackTrace)
              uShipLogger.error("[cause]--" + msg.cause)
              uShipLogger.error("[account]--" + msg.account)
            }else {
              uShipLogger.error("[unknown log type]--" + msg.logType)
              uShipLogger.error("[source url]--" + msg.source)
              uShipLogger.error("[message]--" + msg.msg)
              uShipLogger.debug("[account]--" + msg.account)
              
              return BadRequest(Json.obj("status" ->"Error", "message" -> "logged as error due to unknown log type"))
   
            }
            
            Ok(Json.obj("status" ->"OK", "message" -> ("Log '"+msg.msg+"' saved.") )) 
            
          }
        )
    }
    
}


package models.uLog

case class LogBody(source: String,
                    msg: String,
                    logType:String,
                    account:String,
                    stackTrace:String,
                    cause:String)


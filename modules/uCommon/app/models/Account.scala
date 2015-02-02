package models

import net.liftweb.json._
import play.api.libs.json._

case class Account (

  id: Long,
  firstName: String,
  lastName: String,
  username: String,
  password: String,
  companyId: Int,
  accountStatusId: Int

)

object Account {
  implicit val accountsFormat = Json.format[Account]

  implicit val accountFormat = new Format[Account]{
    def writes(account: Account):JsValue = {
      Json.obj(
        "id" -> account.id,
        "firstName" -> account.firstName,
        "lastName" -> account.lastName,
        "username" -> account.username,
        "password" -> account.password,
        "companyId" -> account.companyId,
        "accountStatusId" -> account.accountStatusId
      )
    }

    def reads(json: JsValue): JsResult[Account] =
      JsSuccess(new Account(
        (json \ "id").as[Long],
        (json \ "firstName").as[String],
        (json \ "lastName").as[String],
        (json \ "username").as[String],
        (json \ "password").as[String],
        (json \ "companyId").as[Int],
        (json \ "accountStatusId").as[Int]
      )
    )
  }

  def create(id: Long,
            firstName: String,
            lastName: String,
            username: String,
            password: String,
            companyId: Int,
            accountStatusId: Int): Boolean = {

    val a = new Account(id,
                        firstName,
                        lastName,
                        username,
                        password,
                        companyId,
                        accountStatusId)

    true

  }

}

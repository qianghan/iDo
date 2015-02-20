package models.uman

import scala.collection.JavaConversions._

import play.api.libs.json.Json
import play.api.data._
import play.api.data.Forms._

case class Account(username:String, password:String)
case class Address(unit:String, line1:String, line2:String, city:String,province:String,country:String,postcode:String)
case class Company(id:Int, name:String, business:String, tel:String, fax:String, email:String, address:Address)
case class Person(email:String,firstName:String,lastName:String,sex:String, age:Int, tel:String, jobtitle:String)
case class SignUp(account:Account, contact:Person, com:Company)
case class Role(id:Int, role:String)
case class Service(id:Int, name:String, srv_type:String)
case class User (token:String, role: Role, services:List[Service])

object JsonFormats {
  implicit val accountFormat = Json.format[Account]
  implicit val addressFormat = Json.format[Address]
  implicit val companyFormat = Json.format[Company]
  implicit val personFormat = Json.format[Person]
  implicit val signUpFormat = Json.format[SignUp]
  implicit val roleFormat = Json.format[Role]
  implicit val serviceFormat = Json.format[Service]
  implicit val userFormat = Json.format[User]
}

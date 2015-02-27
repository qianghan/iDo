package controllers.uman

import play.api._
import play.api.mvc._
import play.mvc.Http
import play.api.libs.json._
import play.api.libs.functional.syntax._


import neo4j.services._
import neo4j.models.uman._
import neo4j.services.uman.UserManagementService
import scala.collection.JavaConverters._
import models.uman.SignUp
import models.uman.User
import models.uman.StatusCode
import play.api.mvc.Results._

import services.uman.impl._
import services.uman._
import services.ServiceFactory


object Formats {
    
  implicit val personReads = new Reads[Person] {
    override def reads(js: JsValue): JsResult[Person] = JsSuccess(
        
      new Person(
        (js \ "firstName").validate[String].get,
        (js \ "lastName").validate[String].get,
        (js \ "age").validate[Int].get,
        (js \ "sex").validate[String].get,
        (js \ "telephone").validate[String].get,
        (js \ "email").validate[String].get
      )
    )
  }

  implicit val personWrites = new Writes[Person] {
      override def writes(person: Person): JsValue = {
       
        val json = Json.obj(
          "firstName" -> person.firstName,
          "lastName" -> person.lastName,
          "age" -> person.age,
          "sex" -> person.sex,
          "telephone" -> person.telephone,
          "email" -> person.email
        )
        
        json 
      }
  }
 
   implicit val addressReads = new Reads[Address] {
     override def reads(js: JsValue): JsResult[Address] = JsSuccess(
      new Address(
        (js \ "unit").validate[String].get,
        (js \ "line1").validate[String].get,
        (js \ "line2").validate[String].get,
        (js \ "city").validate[String].get,
        (js \ "province").validate[String].get,
        (js \ "country").validate[String].get,
        (js \ "postcode").validate[String].get
      )
    )
   }
   
   implicit val addressWrites = new Writes[Address] {
      override def writes(add: Address): JsValue = {
        Json.obj(
          "unit" -> add.unit,
          "line1" -> add.line1,
          "line2" -> add.line2,
          "city" -> add.city,
          "province" -> add.province,
          "country" -> add.country,
          "postcode" -> add.postcode
        )
      }
  }
  
   implicit val companyReads = new Reads[Company] {
    override def reads(js: JsValue): JsResult[Company] = JsSuccess(
      new Company(
        (js \ "companyId").validate[Long].get,
        (js \ "name").validate[String].get,
        (js \ "no_employees").validate[String].get,
        (js \ "tel").validate[String].get,
        (js \ "fax").validate[String].get,
        (js \ "email").validate[String].get,
        (js \ "address").validate[Address].get
      )
    )
   }

  implicit val companyWrites = new Writes[Company] {
      override def writes(com: Company): JsValue = {
        Json.obj(
          "companyId" -> com.companyId.longValue(),
          "name" -> com.name,
          "no_employees" -> com.no_employees,
          "tel" -> com.tel,
          "fax" -> com.fax,
          "email" -> com.email,
          "address" -> Json.toJson(com.address)
        )
      }
  }
  
  implicit val roleReads = new Reads[Role] {
    override def reads(js: JsValue): JsResult[Role] = JsSuccess(
      new Role(
        (js \ "roleId").validate[Long].get,
        (js \ "role").validate[String].get
      )
    )
  }

  implicit val roleWrites = new Writes[Role] {
      override def writes(role: Role): JsValue = {
        Json.obj(
          "roleId" -> role.roleId.longValue(),
          "role" -> role.role
        )
      }
  }
  
 implicit val serviceReads = new Reads[Service] {
    override def reads(js: JsValue): JsResult[Service] = JsSuccess(
      new Service(
        (js \ "serviceId").validate[Long].get,
        (js \ "name").validate[String].get,
        (js \ "description").validate[String].get,
        (js \ "type").validate[String].get,
        (js \ "settings").validate[String].get
      )
    )
  }

  implicit val serviceWrites = new Writes[Service] {
      override def writes(service: Service): JsValue = {
        Json.obj(
          "serviceId" -> service.serviceId.longValue(),
          "name" -> service.name,
          "description" -> service.description,
          "type" -> service.getType(),
          "settings" -> service.settings
         
        )
      }
  }
  
  implicit val accountReads = new Reads[Account] {
    override def reads(js: JsValue): JsResult[Account] = JsSuccess(
      
      new Account(
        (js \ "username").validate[String].get,
        (js \ "password").validate[String].get,
        (js \ "roles").validate[List[Role]].get.toSet.asJava
        )
    )
  }

  implicit val accountWrites = new Writes[Account] {
      override def writes(acc: Account): JsValue = {
        
        val roles = acc.roles.asScala
        
        Json.obj(
          "username" -> acc.username,
          "password" -> acc.password,
          "roles" -> roles.toList
        )
      }
  }
  
  implicit val signupReads = new Reads[SignUp] {
     override def reads(js: JsValue): JsResult[SignUp] = {
       
       val account = (js \ "account").validate[Account].get
       val contact = (js \ "contact").validate[Person].get
       val company = (js \ "company").validate[Company].get
     
       
       JsSuccess(new SignUp(account, contact, company))
  }
 }
   
   implicit val signupWrites = new Writes[SignUp] {
      override def writes(signup: SignUp): JsValue = {
        
        
        Json.obj(
          "account" -> Json.toJson(signup.account),
          "contact" -> Json.toJson(signup.contact),
          "company" -> Json.toJson(signup.company)
        )
      }
  }
   
   implicit val statusCodeWrites = new Writes[StatusCode] {
      override def writes(code: StatusCode): JsValue = {
        
        
        Json.obj(
          "status" -> code.status.intValue(),
          "error" -> code.error,
          "cause" -> code.cause,
          "message" -> code.message,
          "stacktrace" -> code.stacktrace
        )
      }
  }
 
   
   implicit val userWrites = new Writes[User] {
      override def writes(user: User): JsValue = {
        
         val roles = user.roles.asScala
         val services = user.authorizedServices.asScala
        
        Json.obj(
          "account" -> user.account,
          "roles" -> roles.toList,
          "authToken" -> user.authToken,
          "status" -> Json.toJson(user.status),
          "authorizedServices" -> services.toList
        )
      }
  }
   
 implicit val personFormat:Format[Person] =  Format(personReads, personWrites)
 implicit val companyFormat:Format[Company] =  Format(companyReads, companyWrites)
 implicit val roleFormat:Format[Role] =  Format(roleReads, roleWrites)
 implicit val serviceFormat:Format[Service] =  Format(serviceReads, serviceWrites)
 implicit val accountFormat:Format[Account] =  Format(accountReads, accountWrites)
 implicit val signupFormat:Format[SignUp] =  Format(signupReads, signupWrites)
 implicit val addressFormat:Format[Address] =  Format(addressReads, addressWrites)
}
package models.uman

import java.util.List

case class Account(username:String, password:String)
case class Address(unit:String, line1:String, line2:String, city:String,province:String,country:String,postcode:String)
case class Company(id:Int, name:String, business:String, tel:String, fax:String, email:String, address:Address)
case class Person(email:String,firstName:String,lastName:String,sex:String, age:Int, tel:String, jobtitle:String)
case class SignUp(account:Account, contact:Person, com:Company)
case class Role(id:Int, role:String)
case class Service(id:Int, name:String, srv_type:String)
case class User (token:String, role: Role, services:List[Service])

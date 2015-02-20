package services.uman.impl

import services.uman._
import models.uman._

class AccountServicesImpl extends AccountServices {

   def createAccount(account: Account): Account = ???

   def updateAccount(account: Account) {

  }

   def findById(id: Long): Option[Account] = ???

   def deleteAccount(id: Long) {

  }

  override def signup(signup: SignUp): User = ???

  override def login(account: Account): User = ???

}

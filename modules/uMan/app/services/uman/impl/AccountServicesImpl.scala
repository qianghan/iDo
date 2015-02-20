package services.uman.impl

import services.uman._
import models.uman._

class AccountServicesImpl extends AccountServices {

  override def createAccount(account: Account): Account = ???

  override def updateAccount(account: Account) {

  }

  override def findById(id: Long): Option[Account] = ???

  override def deleteAccount(id: Long) {

  }

  def signup(signup: SignUp): User = ???

  def login(account: Account): User = ???

}

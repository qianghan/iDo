package services.uman

import models.uman._

abstract class AccountServices extends LoginService with SignupService {
  def createAccount(account: Account): Account
  def updateAccount(account: Account)
  def findById(id: Long): Option[Account]
  def deleteAccount(id: Long)
}

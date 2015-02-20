package services.uman

import models.uman._


//delegate saving to neo4j.services.UserManagementServices

abstract class AccountServices extends LoginService with SignupService {
  override def signup(signup:SignUp):User ={
    null
  }
}
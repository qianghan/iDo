package services.uman

import models.uman._

trait SignupService {
  def signup(signup:SignUp):User
}
package services.uman

import neo4j.models.uman._

trait SignupService {
  def signup(signup:SignUp):User
}
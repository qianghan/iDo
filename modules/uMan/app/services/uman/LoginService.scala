package services.uman

import models.uman._

trait LoginService {
  def login (account:Account):User
}

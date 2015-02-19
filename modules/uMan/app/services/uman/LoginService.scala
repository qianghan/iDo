package services.uman

import neo4j.models.uman._

trait LoginService {
  def login (account:Account):User
}
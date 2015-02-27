package services.uman

import models.uman._
import neo4j.models.uman.Account

trait AccountServices {
    def signup(signUp: SignUp): User 

    def login(account: Account): User 

}
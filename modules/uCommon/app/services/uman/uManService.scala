package services.uman

import models.uman._
import neo4j.models.uman.Account

import services.Service

trait uManService extends Service{
    def signUp(signUp:SignUp):User
    
    def login(account: Account):User
}
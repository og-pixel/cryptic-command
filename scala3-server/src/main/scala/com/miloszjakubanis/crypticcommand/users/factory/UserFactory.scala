package com.miloszjakubanis.crypticcommand.users.factory
import com.miloszjakubanis.crypticcommand.users.User
import com.miloszjakubanis.crypticcommand.users.SimpleUser
import com.miloszjakubanis.crypticcommand.articles.Article
import com.miloszjakubanis.crypticcommand.users.AdministatorUser

trait UserFactory[T <: User]:
  def createUser(name: String): T
  def createAdmin(name: String): AdministatorUser 
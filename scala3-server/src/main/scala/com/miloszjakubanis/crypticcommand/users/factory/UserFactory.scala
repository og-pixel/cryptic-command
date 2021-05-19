package com.miloszjakubanis.crypticcommand.users.factory
import com.miloszjakubanis.crypticcommand.users.User
import com.miloszjakubanis.crypticcommand.users.SimpleUser
import com.miloszjakubanis.crypticcommand.articles.Article

trait UserFactory[T <: User[_]]:

  def nextUserId: Long
  def createUser(name: String): T

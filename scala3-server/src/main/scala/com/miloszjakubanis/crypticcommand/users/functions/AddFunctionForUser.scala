package com.miloszjakubanis.crypticcommand.users.functions

import com.miloszjakubanis.crypticcommand.users.User
import com.miloszjakubanis.crypticcommand.articles.Article

class AddFunctionForUser {
  
}

object AddFunctionForUser extends UserFunction[UserFunction[_, _], Boolean]:
  def apply(arg: UserFunction[_, _])(using User): Boolean = true


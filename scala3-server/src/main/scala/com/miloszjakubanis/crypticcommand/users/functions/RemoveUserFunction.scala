package com.miloszjakubanis.crypticcommand.users.functions

import com.miloszjakubanis.crypticcommand.users.User
import com.miloszjakubanis.crypticcommand.articles.Article

class RemoveUserFunction {
  
}

object RemoveUserFunction extends UserFunction[String, User]:
  def apply(arg: String)(using User): User = null


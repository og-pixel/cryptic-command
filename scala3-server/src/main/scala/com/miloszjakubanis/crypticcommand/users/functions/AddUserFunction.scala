package com.miloszjakubanis.crypticcommand.users.functions

import com.miloszjakubanis.crypticcommand.users.User
import com.miloszjakubanis.crypticcommand.articles.Article

class AddUserFunction {
  
}

object AddUserFunction extends UserFunction[User, Boolean]:
  def apply(arg: User)(using User): Boolean = true


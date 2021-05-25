package com.miloszjakubanis.crypticcommand.users.functions

import com.miloszjakubanis.crypticcommand.users.User
import com.miloszjakubanis.crypticcommand.articles.Article

class RemoveArticleFunction 

object RemoveArticleFunction extends UserFunction[String, Article[_]]:
  def apply(arg: String)(using User): Article[_] = null


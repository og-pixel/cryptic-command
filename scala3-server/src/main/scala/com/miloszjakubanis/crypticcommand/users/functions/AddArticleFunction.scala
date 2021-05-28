package com.miloszjakubanis.crypticcommand.users.functions

import com.miloszjakubanis.crypticcommand.users.User
import com.miloszjakubanis.crypticcommand.articles.Article

object AddArticleFunction extends UserFunction[Article[_], Option[Article[_]]]:
  def apply(arg: Article[_])(using user: User): Option[Article[_]] = 
    user.storage().put(arg.title, arg)
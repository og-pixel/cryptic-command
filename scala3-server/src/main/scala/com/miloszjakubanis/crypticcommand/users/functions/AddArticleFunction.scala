package com.miloszjakubanis.crypticcommand.users.functions

import com.miloszjakubanis.crypticcommand.users.User
import com.miloszjakubanis.crypticcommand.articles.Article


class AddArticleFunction extends UserFunction[Article[_], Option[Article[_]]] {
  def apply(args: Article[_])(using User): Option[Article[_]] = AddArticleFunction(args)
  val z = ""
}

object AddArticleFunction extends UserFunction[Article[_], Option[Article[_]]]:
  def apply(arg: Article[_])(using user: User): Option[Article[_]] = 
    user.storage().put("", arg)


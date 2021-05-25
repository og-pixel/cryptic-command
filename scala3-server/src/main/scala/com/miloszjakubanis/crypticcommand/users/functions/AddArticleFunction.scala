package com.miloszjakubanis.crypticcommand.users.functions

import com.miloszjakubanis.crypticcommand.users.User
import com.miloszjakubanis.crypticcommand.articles.Article


abstract class AddArticleFunction extends UserFunction[Article[_], Option[Article[_]]]:
  def apply(args: Article[_])(using User): Option[Article[_]] = AddArticleFunction(args)


object AddArticleFunction extends UserFunction[Article[_], Option[Article[_]]]:
  def apply(arg: Article[_])(using user: User): Option[Article[_]] = 
    user.storage().put("", arg)


object SuperAddArticleFunction extends AddArticleFunction:
  override def apply(args: Article[_])(using User): Option[Article[_]] =
    val result = AddArticleFunction(args)
    println("I have used special function")
    result

class SuperAddArticleFunction extends AddArticleFunction:
  override def apply(args: Article[_])(using User): Option[Article[_]] =
    val result = AddArticleFunction(args)
    println("I have used special function")
    result
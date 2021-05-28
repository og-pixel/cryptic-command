package com.miloszjakubanis.crypticcommand.users

import java.util.Collection

import com.miloszjakubanis.crypticcommand.articles.Article
import com.miloszjakubanis.crypticcommand.users.functions.*
import com.miloszjakubanis.crypticcommand.users.privilege.*
import com.miloszjakubanis.crypticcommand.users.storage.Storage
import com.miloszjakubanis.crypticcommand.users.storage.SimpleNestedStorage


trait User {

  private[this] given User = this
  val userID: Long
  val userName: String

  val storage: SimpleNestedStorage[Article[_]]
  private[this] var _privilege: PrivilegeState = BasicUserPrivilege

  def changePrivilege(privilege: PrivilegeState): Unit = 
    this._privilege = privilege

  def addArticle(article: Article[_]): Option[Article[_]] =
    apply(AddArticleFunction, article)

  def removeArticle(key: String): Option[Article[_]] =
    storage.storage.remove(key)

  def findArticle(key: String): Option[Article[_]] = 
    storage.storage.get(key)

  def privilege = _privilege

  def runFunction[Input, Output](x: UserFunction[Input, Output], input: Input): Output = 
    apply(x, input)

  def apply[Input, Output](x: UserFunction[Input, Output], input: Input): Output =
    x(input)
    // if privilege.list.contains(x) then  x.apply(input)
    // else throw new RuntimeException("this uses is not allowed to execute this command")

}
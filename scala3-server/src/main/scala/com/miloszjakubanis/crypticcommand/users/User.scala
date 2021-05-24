package com.miloszjakubanis.crypticcommand.users

import java.util.Collection
import com.miloszjakubanis.crypticcommand.articles.Article
import com.miloszjakubanis.crypticcommand.users.privilege.*


trait User/*[T <: Article[_]]*/ {

  private[this] given User = this
  val userID: Long
  val userName: String

  val storage: SimpleNestedStorage[Article[_]]
  private[this] var _privilege: PrivilegeState = BasicUserPrivilege

  def changePrivilege(privilege: PrivilegeState): Unit = 
    this._privilege = privilege

  def addArticle(article: Article[_]): Option[Article[_]] =
   storage.storage.put(article.title, article)

  def removeArticle(key: String): Option[Article[_]] =
   storage.storage.remove(key)

  def findArticle(key: String): Option[Article[_]] = 
   storage.storage.get(key)

  def privilege = _privilege

  def apply[Input, Output](x: UserFunction[Input, Output], input: Input): Output =
    if privilege.list.contains(x) then  x.apply(input)
    else throw new RuntimeException("this uses is not allowed to execute this command")

}
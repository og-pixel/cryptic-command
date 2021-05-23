package com.miloszjakubanis.crypticcommand.users

import java.util.Collection
import com.miloszjakubanis.crypticcommand.articles.Article
import com.miloszjakubanis.crypticcommand.users.privilege.*


trait User[T <: Article[_]] {

  val userID: Long
  val userName: String

  val storage: SimpleNestedStorage[T]
  private[this] var _privilege: PrivilegeState = BasicUserPrivilege

  def changePrivilege(privilege: PrivilegeState): Unit = 
    this._privilege = privilege

  def addArticle(article: T): Option[T] =
   storage.storage.put(article.title, article)

  def removeArticle(key: String): Option[T] =
   storage.storage.remove(key)

  def findArticle(key: String): Option[T] = 
   storage.storage.get(key)

  def privilege = _privilege

  def callPrivilege[Input, Output](x: Privilege[Input, Output], input: Input): Output =
    if privilege.list.contains(x) then  x.executePrivilege(input)
    else throw new RuntimeException("this uses is not allowed to execute this command")

}
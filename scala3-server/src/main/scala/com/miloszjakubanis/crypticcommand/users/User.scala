package com.miloszjakubanis.crypticcommand.users

import java.util.Collection
import com.miloszjakubanis.crypticcommand.articles.Article
import com.miloszjakubanis.crypticcommand.users.privilege.PrivilegeState
import com.miloszjakubanis.crypticcommand.users.privilege.BasicUserPrivilege
import com.miloszjakubanis.crypticcommand.users.privilege.Privilege


trait User[T <: Article[_]] {

  val userID: Long
  val userName: String

  val storage: SimpleNestedStorage[T]
  var privilege: PrivilegeState

  def executePrivilege(privilege: Privilege): Unit = ???

  def addArticle(article: T): Option[T] =
   storage.storage.put(article.title, article)

  def removeArticle(key: String): Option[T] =
   storage.storage.remove(key)

  def findArticle(key: String): Option[T] = 
   storage.storage.get(key)

}
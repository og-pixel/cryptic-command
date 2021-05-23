package com.miloszjakubanis.crypticcommand.users.privilege

import scala.collection.mutable.ArrayBuffer
import com.miloszjakubanis.crypticcommand.users.{User, SimpleUser}
import com.miloszjakubanis.crypticcommand.users.AdministatorUser
import com.miloszjakubanis.crypticcommand.articles.Article

sealed trait Privilege[Input, Output]:
  def executePrivilege(arg: Input): Output

object AddArticlePrivilege extends Privilege[Article[_], Boolean]:
  def executePrivilege(arg: Article[_]): Boolean = true

object RemoveArticlePrivilege extends Privilege[String, Article[_]]:
  def executePrivilege(arg: String): Article[_] = null

object AddUsersPrivilege extends Privilege[User[_], Boolean]:
  def executePrivilege(arg: User[_]): Boolean = true

object RemoveUserPrivilege extends Privilege[String, User[_]]:
  def executePrivilege(arg: String): User[_] = null

object AddPrivilegeForUserPrivilege extends Privilege[Privilege[_, _], Boolean]:
  def executePrivilege(arg: Privilege[_, _]): Boolean = true

object RemovePrivilegeForUserPrivilege extends Privilege[Privilege[_, _], Boolean]:
  def executePrivilege(arg: Privilege[_, _]): Boolean = true

/////////////////////////////////////////

trait PrivilegeState:
  //TODO change to mutable data structure, some sort of map maybe too
  val list: Array[Privilege[_, _]]

//TODO Maybe they need to be classes
object BasicUserPrivilege extends PrivilegeState:
  override val list = Array(
    AddArticlePrivilege,
     RemoveArticlePrivilege
     )

object AdministatorPrivilege extends PrivilegeState:
  override val list = Array(
    AddArticlePrivilege,
    RemoveArticlePrivilege,
    AddUsersPrivilege,
    RemoveUserPrivilege,
    AddPrivilegeForUserPrivilege,
    RemovePrivilegeForUserPrivilege
  )
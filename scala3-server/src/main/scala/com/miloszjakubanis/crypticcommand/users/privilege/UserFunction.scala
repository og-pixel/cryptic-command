package com.miloszjakubanis.crypticcommand.users.privilege

import scala.collection.mutable.ArrayBuffer
import com.miloszjakubanis.crypticcommand.users.{User, SimpleUser}
import com.miloszjakubanis.crypticcommand.users.AdministatorUser
import com.miloszjakubanis.crypticcommand.articles.Article

sealed trait UserFunction[Input, Output]:

  def apply(arg: Input)(using User): Output

  def listAvaliblePrivileages: Unit = 
    println("AddArticlePrivilege")
    println("RemoveArticlePrivilege")
    println("AddUsersPrivilege")
    println("RemoveUserPrivilege")
    println("AddPrivilegeForUserPrivilege")
    println("RemovePrivilegeForUserPrivilege")
    
object AddArticleFunction extends UserFunction[Article[_], Boolean]:
  def apply(arg: Article[_])(using user: User): Boolean = 
    user.storage().put("", arg)
    true
    


object RemoveArticleFunction extends UserFunction[String, Article[_]]:
  def apply(arg: String)(using User): Article[_] = null

object AddUsersFunction extends UserFunction[User, Boolean]:
  def apply(arg: User)(using User): Boolean = true

object RemoveUserFunction extends UserFunction[String, User]:
  def apply(arg: String)(using User): User = null

object AddFunctionForUser extends UserFunction[UserFunction[_, _], Boolean]:
  def apply(arg: UserFunction[_, _])(using User): Boolean = true

object RemoveFunctionForUser extends UserFunction[UserFunction[_, _], Boolean]:
  def apply(arg: UserFunction[_, _])(using User): Boolean = true

/////////////////////////////////////////

trait PrivilegeState:
  //TODO change to mutable data structure, some sort of map maybe too
  val list: Array[UserFunction[_, _]]

//TODO Maybe they need to be classes
object BasicUserPrivilege extends PrivilegeState:
  override val list = Array(
    AddArticleFunction,
     RemoveArticleFunction
     )

object AdministatorPrivilege extends PrivilegeState:
  override val list = Array(
    AddArticleFunction,
    RemoveArticleFunction,
    AddUsersFunction,
    RemoveUserFunction,
    AddFunctionForUser,
    RemoveFunctionForUser
  )
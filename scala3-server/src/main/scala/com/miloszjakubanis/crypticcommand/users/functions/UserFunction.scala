package com.miloszjakubanis.crypticcommand.users.functions

import scala.collection.mutable.ArrayBuffer
import com.miloszjakubanis.crypticcommand.users.{User, SimpleUser}
import com.miloszjakubanis.crypticcommand.users.AdministatorUser
import com.miloszjakubanis.crypticcommand.articles.Article

trait UserFunction[Input, Output]:
 
  def apply(arg: Input)(using User): Output

  def listAvaliblePrivileages: Unit = 
    println("AddArticlePrivilege")
    println("RemoveArticlePrivilege")
    println("AddUsersPrivilege")
    println("RemoveUserPrivilege")
    println("AddPrivilegeForUserPrivilege")
    println("RemovePrivilegeForUserPrivilege")

/////////////////////////////////////////

// trait PrivilegeState:
//   //TODO change to mutable data structure, some sort of map maybe too
//   val list: Array[UserFunction[_, _]]

// //TODO Maybe they need to be classes
// object BasicUserPrivilege extends PrivilegeState:
//   override val list = Array(
//     AddArticleFunction,
//     RemoveArticleFunction
//   )

// class BasicUserPrivilege(additionalPrivilege: Array[UserFunction[_, _]]) extends PrivilegeState:
//   override val list: Array[UserFunction[_, _]] =
//     BasicUserPrivilege.list ++ additionalPrivilege

// object AdministatorPrivilege extends PrivilegeState:
//   override val list = Array(
//     AddArticleFunction,
//     RemoveArticleFunction,
//     AddUserFunction,
//     RemoveUserFunction,
//     AddFunctionForUser,
//     RemoveFunctionForUser
//   )
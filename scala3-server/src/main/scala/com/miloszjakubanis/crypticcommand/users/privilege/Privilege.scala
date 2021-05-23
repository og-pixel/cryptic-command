package com.miloszjakubanis.crypticcommand.users.privilege

import scala.collection.mutable.ArrayBuffer

trait Privilege:
  def executePrivilege: Unit

object AddArticlePrivilege extends Privilege:
  def executePrivilege: Unit = println

object RemoveArticlePrivilege extends Privilege:
  def executePrivilege: Unit = println

object AddUsersPrivilege extends Privilege:
  def executePrivilege: Unit = println

object RemoveUserPrivilege extends Privilege:
  def executePrivilege: Unit = println

object AddPrivilegeForUserPrivilege extends Privilege:
  def executePrivilege: Unit = println

object RemovePrivilegeForUserPrivilege extends Privilege:
  def executePrivilege: Unit = println

/////////////////////////////////////////

trait PrivilegeState:
  val list: Array[Privilege]
  def listPrivilages = list.foreach(e => println(e.getClass))

object BasicUserPrivilege extends PrivilegeState:
  override val list = Array(AddArticlePrivilege, RemoveArticlePrivilege)

object AdministatorPrivilege extends PrivilegeState:
  override val list = Array(
    AddArticlePrivilege,
    RemoveArticlePrivilege,
    AddUsersPrivilege,
    RemoveUserPrivilege,
    )
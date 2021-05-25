package com.miloszjakubanis.crypticcommand.users.factory
import com.miloszjakubanis.crypticcommand.articles.{Article, SimpleArticle}
import com.miloszjakubanis.crypticcommand.users.{SimpleUser, User, AdministatorUser}
// import com.miloszjakubanis.crypticcommand.users.functions.BasicUserPrivilege
// import com.miloszjakubanis.crypticcommand.users.functions.PrivilegeState
import com.miloszjakubanis.crypticcommand.users.privilege.BasicUserPrivilege
import com.miloszjakubanis.crypticcommand.users.privilege.PrivilegeState


/**
 * The most basic factory
 * holds id for users
 */
class SimpleUserFactory extends UserFactory[SimpleUser]:

  private[this] var nextId: Long = 0
  private[this] def nextUserId: Long = 
    nextId += 1
    nextId

  def createUser(name: String): SimpleUser =
    SimpleUser(name, nextUserId)

  def createUserCustom(name: String, privilege: PrivilegeState): SimpleUser =
    SimpleUser(name, nextUserId, privilege)

  def createAdmin(name: String): AdministatorUser =  
    AdministatorUser(name, nextUserId)
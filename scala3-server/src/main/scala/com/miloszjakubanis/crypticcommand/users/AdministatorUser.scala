package com.miloszjakubanis.crypticcommand.users
import com.miloszjakubanis.crypticcommand.articles.*
import com.miloszjakubanis.crypticcommand.users.functions.*
import com.miloszjakubanis.crypticcommand.users.privilege.*

class AdministatorUser(val userName: String, val userID: Long) extends User:
  private var _privilege: PrivilegeState = AdministatorPrivilege
  val storage: SimpleNestedStorage[Article[_]] = SimpleNestedStorage()
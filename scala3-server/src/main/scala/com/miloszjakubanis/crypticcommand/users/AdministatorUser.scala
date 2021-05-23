package com.miloszjakubanis.crypticcommand.users
import com.miloszjakubanis.crypticcommand.articles.*
import com.miloszjakubanis.crypticcommand.users.privilege.*

class AdministatorUser(val userName: String, val userID: Long) extends User[SimpleArticle]:
  private var _privilege: PrivilegeState = AdministatorPrivilege
  val storage: SimpleNestedStorage[SimpleArticle] = SimpleNestedStorage()
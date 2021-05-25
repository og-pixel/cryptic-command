package com.miloszjakubanis.crypticcommand.users.privilege

import com.miloszjakubanis.crypticcommand.users.functions.*

object AdministatorPrivilege extends PrivilegeState:
  override val list = Array(
    AddArticleFunction,
    RemoveArticleFunction,
    AddUserFunction,
    RemoveUserFunction,
    AddFunctionForUser,
    RemoveFunctionForUser
  )

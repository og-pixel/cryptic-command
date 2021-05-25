package com.miloszjakubanis.crypticcommand.users.privilege

import com.miloszjakubanis.crypticcommand.users.functions.*

class BasicUserPrivilege(additionalPrivilege: Array[UserFunction[_, _]]) extends PrivilegeState:
  override val list: Array[UserFunction[_, _]] =
    BasicUserPrivilege.list ++ additionalPrivilege


object BasicUserPrivilege extends PrivilegeState:
  override val list = Array(
    AddArticleFunction,
    RemoveArticleFunction
  )


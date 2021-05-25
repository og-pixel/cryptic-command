package com.miloszjakubanis.crypticcommand.users.privilege

import com.miloszjakubanis.crypticcommand.users.functions.UserFunction

trait PrivilegeState:
  //TODO change to mutable data structure, some sort of map maybe too
  val list: Array[UserFunction[_, _]]


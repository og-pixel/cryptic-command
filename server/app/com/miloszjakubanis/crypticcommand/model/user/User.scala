package com.miloszjakubanis.crypticcommand.model.user

import com.miloszjakubanis.crypticcommand.model.user.permission.Permissions

trait User {
  val userName: String
  val userID: Int
  val permission: Permissions
}

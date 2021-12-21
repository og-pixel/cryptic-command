package com.miloszjakubanis.crypticcommand.model.user

import com.miloszjakubanis.crypticcommand.model.user.permission.UserPermission

trait User {
  val userName: String
  val userID: Int
  val permission: UserPermission
}

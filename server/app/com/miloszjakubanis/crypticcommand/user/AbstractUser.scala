package com.miloszjakubanis.crypticcommand.user

import com.miloszjakubanis.crypticcommand.user.permission.UserPermission


case class AbstractUser(val userName: String, val userID: Int, val permission: UserPermission) extends User {


}

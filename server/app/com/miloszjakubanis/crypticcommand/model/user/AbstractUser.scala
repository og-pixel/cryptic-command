package com.miloszjakubanis.crypticcommand.model.user

import com.miloszjakubanis.crypticcommand.model.user.permission.UserPermission


class AbstractUser(val userName: String, val userID: Int, val permission: UserPermission) extends User {


}

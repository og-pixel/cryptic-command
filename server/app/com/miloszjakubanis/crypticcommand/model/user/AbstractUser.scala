package com.miloszjakubanis.crypticcommand.model.user

import com.miloszjakubanis.crypticcommand.model.user.permission.Permissions

abstract class AbstractUser(val userName: String, val userID: Int, val permission: Permissions) extends User
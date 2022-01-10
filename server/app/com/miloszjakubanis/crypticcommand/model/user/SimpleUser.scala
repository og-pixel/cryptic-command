package com.miloszjakubanis.crypticcommand.model.user
import com.miloszjakubanis.crypticcommand.model.user.permission.Permissions

class SimpleUser(userName: String, userID: Int)
    extends AbstractUser(userName, userID, UserPermi)

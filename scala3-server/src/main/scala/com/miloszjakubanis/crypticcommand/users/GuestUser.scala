package com.miloszjakubanis.crypticcommand.users

import com.miloszjakubanis.crypticcommand.articles.Article
import com.miloszjakubanis.crypticcommand.users.storage.{Storage, SimpleNestedStorage}

object GuestUser extends User:
  val userName: String = "Guest"
  val userID: Long = -1
  val storage: SimpleNestedStorage[Article[_]] = null
package com.miloszjakubanis.crypticcommand.users

import java.util.Collection
import com.miloszjakubanis.crypticcommand.articles.Article

trait User[T <: Article[_]] {

  val userID: Long
  val userName: String

  val collection: Iterable[T]
}

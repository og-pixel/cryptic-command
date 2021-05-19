package com.miloszjakubanis.crypticcommand.users
import com.miloszjakubanis.crypticcommand.articles.Article
import java.util.Collection
import scala.collection.mutable.ArrayBuffer
import com.miloszjakubanis.crypticcommand.articles.SimpleArticle

class SimpleUser(val userName: String, val userID: Long) extends User[SimpleArticle]:

  override val collection: Iterable[SimpleArticle] = ArrayBuffer()
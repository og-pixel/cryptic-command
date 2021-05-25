package com.miloszjakubanis.crypticcommand.users
import com.miloszjakubanis.crypticcommand.articles.Article
import java.util.Collection
import scala.collection.mutable.ArrayBuffer
import com.miloszjakubanis.crypticcommand.articles.SimpleArticle
import com.miloszjakubanis.crypticcommand.users.SimpleNestedNodeStorage
import scala.collection.mutable.HashMap
import com.miloszjakubanis.crypticcommand.users.privilege.PrivilegeState
import com.miloszjakubanis.crypticcommand.users.privilege.BasicUserPrivilege

class SimpleUser(
  val userName: String,
  val userID: Long,
  private var _privilege:PrivilegeState = BasicUserPrivilege,
  ) extends User:
  override val storage: SimpleNestedStorage[Article[_]] = SimpleNestedStorage()
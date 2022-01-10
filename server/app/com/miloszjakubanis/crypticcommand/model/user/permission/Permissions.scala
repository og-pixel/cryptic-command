package com.miloszjakubanis.crypticcommand.model.user.permission

import scala.collection.immutable
import scala.collection.immutable.HashMap
import scala.collection.mutable

sealed trait Permission
object AddUserPermission extends Permission
object AddArticlePermission extends Permission

//TODO for now it is a simple implementation
abstract class Permissions(perm: immutable.HashMap[Permission, Boolean] = new HashMap()) {
  protected[this] val permissions: mutable.HashMap[Permission, Boolean] = new mutable.HashMap() ++ perm
  def apply(key: Permission): Boolean = permissions(key)
}

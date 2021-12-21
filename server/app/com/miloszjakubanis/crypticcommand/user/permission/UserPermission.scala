package com.miloszjakubanis.crypticcommand.user.permission

import scala.collection.mutable

trait UserPermission {
  private[this] val permissions: mutable.HashMap[String, String] = new mutable.HashMap()
  def apply(key: String): String = permissions(key)
}

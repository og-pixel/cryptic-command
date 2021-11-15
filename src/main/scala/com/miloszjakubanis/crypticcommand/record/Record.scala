package com.miloszjakubanis.crypticcommand.record

trait Record[V] {
  val key: Int
  val value: V
}

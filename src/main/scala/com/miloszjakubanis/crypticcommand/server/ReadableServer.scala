package com.miloszjakubanis.crypticcommand.server

import com.miloszjakubanis.thoughtseize.storage.Location
import com.typesafe.scalalogging.StrictLogging

import java.nio.file.Path

class ReadableServer(val location: Location) extends AutoCloseable with StrictLogging {

  val root: Path = location()
  override def close(): Unit = ???
}

package com.miloszjakubanis.crypticcommand.server

import com.miloszjakubanis.thoughtseize.storage.Location
import com.typesafe.scalalogging.StrictLogging

class ReadableServer(val location: Location) extends AutoCloseable with StrictLogging {
  override def close(): Unit = ???
}

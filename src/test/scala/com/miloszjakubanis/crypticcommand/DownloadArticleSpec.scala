package com.miloszjakubanis.crypticcommand

import com.miloszjakubanis.crypticcommand.server.ReadableServer
import com.miloszjakubanis.thoughtseize.storage.Location
import utest._
import com.typesafe.scalalogging.StrictLogging

object DownloadArticleSpec extends TestSuite with StrictLogging {

  override def tests: Tests = Tests {
    test("hello world") - {
      val server = new ReadableServer(Location("data"))
    }
  }
}

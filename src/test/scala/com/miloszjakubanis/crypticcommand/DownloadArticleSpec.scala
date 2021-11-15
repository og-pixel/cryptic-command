package com.miloszjakubanis.crypticcommand

import com.miloszjakubanis.crypticcommand.server.{ReadableServer, ServerConfig}
import com.miloszjakubanis.thoughtseize.storage.{FSDatabase, Location, LocationPlace}
import com.typesafe.config.Config
import utest._
import com.typesafe.scalalogging.StrictLogging

object DownloadArticleSpec extends TestSuite with StrictLogging {

  override def tests: Tests = Tests {
    test("hello world") - {
      def start(c: Config): Unit = {
        val db = new FSDatabase(Location("data", LocationPlace.ROOT))
        val server = new ReadableServer(db, new ServerConfig(c))

//        server.close()

      }

      DefaultParser(Array[String]("-pi", "9999"), start)
    }
  }
}

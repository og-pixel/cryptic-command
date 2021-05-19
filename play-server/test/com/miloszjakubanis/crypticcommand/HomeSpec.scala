package com.miloszjakubanis.crypticcommand

import com.miloszjakubanis.crypticcommand.readability.{Connected, Connector, SimpleConnector}
import utest.{TestSuite, Tests, test}

object HomeSpec extends TestSuite {
  override def tests: Tests = Tests {
    val testWebsite = "https://www.bbc.co.uk/newsround/56860192"

    test("readability") - {
      val readability = new SimpleConnector
      val connectionState = readability.connectionState
      var result: String = ""

      if(connectionState eq Connected) result = readability.getArticle(testWebsite)

      assert(readability.connectionState eq Connected)
      assert(result.nonEmpty)
      println(result)


    }

  }
}

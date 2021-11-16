package com.miloszjakubanis.crypticcommand

import com.typesafe.scalalogging.StrictLogging
import utest.{TestSuite, Tests, test}

object HelloWorldClientSpec extends TestSuite with StrictLogging {
  override def tests: Tests = Tests {
    test("Hello world") - {
    }
  }
}

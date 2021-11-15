package com.miloszjakubanis.crypticcommand

import com.typesafe.config.Config
import com.typesafe.scalalogging.StrictLogging
import utest.{TestSuite, Tests, test}

object ConfigParserSpec extends TestSuite with StrictLogging {
  override def tests: Tests = Tests {

    val correctArgs = Array(
      "-pi",
      "11",
      "-po",
      "22"
    )
    val incorrectArgs = Array("-pp", "233")

    test("Create parser with default args") - {
      val config =
        MainParser.parser.parse(Array[String](), MainParser.config).get
      assertValue(config, "port.in", "1")
      assertValue(config, "port.out", "2")
    }

    test("Create parser with args") - {
      val config =
        MainParser.parser.parse(correctArgs, MainParser.config).get
      assertValue(config, "port.in", "11")
      assertValue(config, "port.out", "22")
    }

    def assertValue(cfg: Config, value: String, expected: String): Unit =
      assert(
        cfg.getString(value) == expected,
        s"Found: ${cfg.getString(value)} Expected: $expected"
      )
  }
}

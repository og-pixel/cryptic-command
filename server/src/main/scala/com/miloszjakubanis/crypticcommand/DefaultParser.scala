package com.miloszjakubanis.crypticcommand

import com.typesafe.config.{Config, ConfigFactory, ConfigValueFactory}
import helloServer.BuildInfo
import scopt.OptionParser

import scala.jdk.CollectionConverters.MapHasAsJava

object DefaultParser {

  private[this] val defaultConfigValue: Config = ConfigFactory.parseMap(
    (BuildInfo.toMap ++
      Map[String, Object](
        "port.in" -> "3400",
        "port.out" -> "3401"
      )).asJava,
    "Default config."
  )

  val config: Config = ConfigFactory.load().withFallback(defaultConfigValue)

  val z = "asdasd"
  z.toString

  val parser: OptionParser[Config] =
    new scopt.OptionParser[Config](BuildInfo.name) {
      head(BuildInfo.name, BuildInfo.version)
      opt[String]("port-in")
        .abbr("pi")
        .action((v, c) =>
          c.withValue("port.in", ConfigValueFactory.fromAnyRef(v))
        )
        .text(s"Receiving Port.")
      opt[String]("port-out")
        .abbr("po")
        .action((v, c) =>
          c.withValue("port.out", ConfigValueFactory.fromAnyRef(v))
        )
        .text(s"Sending Port.")
      help("help").abbr("h").text("Print this help")

    }

  def parse(args: Array[String], startFun: Config => Unit): Unit = {
    parser.parse(args, config) match {
      case Some(value) =>
        val a = value.resolve()
        startFun(a)
      case None =>
    }
  }

  def apply(args: Array[String], startFun: Config => Unit): Unit =
    parse(args, startFun)

}

package com.miloszjakubanis.crypticcommand

import com.typesafe.config.{Config, ConfigFactory, ConfigValueFactory}
import hello.BuildInfo
import scopt.OptionParser

import scala.jdk.CollectionConverters.MapHasAsJava

object MainParser {

  private[this] val defaultConfigValue: Config = ConfigFactory.parseMap(
    (BuildInfo.toMap ++
      Map[String, Object](
        "port.in" -> "3400",
        "port.out" -> "3401",
      )).asJava, "Default config."
  )

  val defaultConfig: Config = ConfigFactory.load().withFallback(defaultConfigValue)


  val parser: OptionParser[Config] = new scopt.OptionParser[Config](BuildInfo.name) {
    head(BuildInfo.name, BuildInfo.version)
//    version(BuildInfo.version)
    opt[String]("port-in").abbr("pi")
      .action((v, c) => c.withValue("port.in", ConfigValueFactory.fromAnyRef(v)))
      .text(s"Receiving Port.")
    opt[String]("port-out").abbr("po")
      .action((v, c) => c.withValue("port.out", ConfigValueFactory.fromAnyRef(v)))
      .text(s"Sending Port.")
    help("help").abbr("h").text("Print this help")

  }

  private[this] def start(config: Config): Unit = {
    val a = config.getString("port.in")
    println(s"value a: ${a}")
  }

  def parse(args: Array[String]): Unit = {
    parser.parse(args, defaultConfig) match {
      case Some(value) =>
        val a = value.resolve()
        start(a)
      case None =>
    }
  }

  def apply(args: Array[String]): Unit = parse(args)

}
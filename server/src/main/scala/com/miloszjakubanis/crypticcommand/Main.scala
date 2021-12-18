package com.miloszjakubanis.crypticcommand

import com.typesafe.config.Config
import com.typesafe.config.ConfigValue
import scala.sys.process._
import java.net.URL
import io.circe._
import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._
import com.miloszjakubanis.crypticcommand.user.AbstractUser

object Main extends App {

  val parser = DefaultParser(args, start)

  def start(c: Config): Unit = {
    val adasda = "hashda"
    println("hello world")
    val z: ConfigValue = c.getValue("port.in")
    println(z)
    val pp = new URL("https://google.com")
    // val zzz = new Lol("Milosz", 25).asJson
    // println(s"As JSon: $zzz")
  }

  val p = new AbstractUser("", 1)


}


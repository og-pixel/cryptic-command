package com.miloszjakubanis.crypticcommand

import com.miloszjakubanis.crypticcommand.server.{ReadableServer, ServerConfig}
import com.typesafe.config.Config
import com.typesafe.config.ConfigValue

import scala.sys.process._
import java.net.URL
import io.circe._
import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._
import com.miloszjakubanis.crypticcommand.user.AbstractUser
import com.miloszjakubanis.thoughtseize.storage.{FSDatabase, Location, LocationPlace}

object Main {

//  DefaultParser(args, start)

//  private[this] def start(c: Config): Unit = {
//    println(c.getString("port.in"))
//    val a = new ReadableServer(new FSDatabase(Location("tmp-server", LocationPlace.TMP)), c)
//  }

}


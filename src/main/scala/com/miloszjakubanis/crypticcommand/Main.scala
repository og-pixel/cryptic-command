package com.miloszjakubanis.crypticcommand

import com.typesafe.config.Config

object Main extends App {

  val parser = DefaultParser(args, start)

  def start(c: Config): Unit = {
    println("hello world")
  }


}

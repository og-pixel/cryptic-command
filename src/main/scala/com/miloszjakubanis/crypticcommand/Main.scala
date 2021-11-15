package com.miloszjakubanis.crypticcommand

import com.typesafe.config.Config

object Main extends App {

  def start(c: Config): Unit = {
    println("hello world")
  }

  val parser = MainParser(args, start)

}

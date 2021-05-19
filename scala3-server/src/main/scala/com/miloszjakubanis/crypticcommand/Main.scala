package com.miloszjakubanis.crypticcommand

import com.miloszjakubanis.crypticcommand.users.factory.SimpleUserFactory

object Main {

  def main(args: Array[String]) = {
    val factory = SimpleUserFactory()
    val a = factory.createUser("Milosz")
    val b = factory.createUser("Thoams")
    val c = factory.createUser("Patrick")

    Array(a, b, c).foreach(e => {
      println(s"""
      |ID:     : ${e.userID}
      |Username: ${e.userName}
      |
    """.stripMargin)
    })

  }
}

package com.miloszjakubanis.crypticcommand

import com.miloszjakubanis.crypticcommand.users.factory.SimpleUserFactory
import org.apache.commons.cli.*
import com.miloszjakubanis.crypticcommand.users.AdministatorUser
import com.miloszjakubanis.crypticcommand.users.User
import com.miloszjakubanis.crypticcommand.articles.SimpleArticle
// import org.apache.commons.cli.CommandLineParser
// import org.apache.commons.cli.DefaultParser

object Main {

  def main(args: Array[String]) = {
    val factory = SimpleUserFactory()
    val a = factory.createUser("Milosz")
    val b = factory.createUser("Thoams")
    val c = factory.createUser("Patrick")
    val d = factory.createUser("Richard")

    Array(a, b, c, d).foreach(e => {
      println(s"""
      |ID:     : ${e.userID}
      |Username: ${e.userName}
      |
    """.stripMargin)
    })

    val options = Options()
    options.addOption("a", false, "Add two numbers")
    val parser: CommandLineParser = DefaultParser()

    val cmd: CommandLine = parser.parse(options, args)

    if cmd.hasOption("a") 
    then println("you chose option a")
    else println("you didn't!")

    a.privilege.listPrivilages
    a.addArticle(SimpleArticle())
  }

  // def addOption1 = ???
  // def addOption2 = ???
  // def addOption3 = ???

}

// object MinimalApplication extends cask.MainRoutes:
//   @cask.get("/")
//   def hello() = 
//     "Hello World!"
  
//   @cask.post("/do-thing")
//   def doThing(request: cask.Request) = 
//     request.text().reverse
  
//   initialize()
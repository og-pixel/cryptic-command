package com.miloszjakubanis.crypticcommand
import com.miloszjakubanis.crypticcommand.users.factory.SimpleUserFactory
import com.miloszjakubanis.crypticcommand.users.{User, AdministatorUser}
import com.miloszjakubanis.crypticcommand.articles.SimpleArticle
import com.miloszjakubanis.crypticcommand.users.functions.{RemoveUserFunction, AddArticleFunction}
import com.miloszjakubanis.crypticcommand.users.functions.UserFunction
import com.miloszjakubanis.crypticcommand.users.privilege.*

@main def hello =
  val factory = SimpleUserFactory()
  val admin = factory.createAdmin("og_pixel")
  val user1 = factory.createUser("pixel")
  val user2 = factory.createUser("Daniel")
  val userSpecial = factory.createUserCustom("Cusotm", new BasicUserPrivilege(Array()))

  Array(admin, user1, user2, userSpecial).foreach(e => 
  println(
  s"""|ID: ${e.userID}
      |Name: ${e.userName}
  """.stripMargin))

  // user1(new AddArticleFunction(), SimpleArticle("HELLO"))
  // userSpecial(new AddArticleFunction(), SimpleArticle("HELLO"))
  // userSpecial(AddArticleFunction, SimpleArticle("HELLO"))

  // val z = user1.runFunction[String, String](new UserFunction[String, String] {
  //   def apply(arg: String)(using user: User): String = arg
  // }, "hello custom pass")


  user1.storage().foreach((k,v) => {
    println(k)
    println(v.content)
  })

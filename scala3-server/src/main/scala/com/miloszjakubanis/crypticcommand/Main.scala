package com.miloszjakubanis.crypticcommand
import com.miloszjakubanis.crypticcommand.users.factory.SimpleUserFactory
import com.miloszjakubanis.crypticcommand.users.{User, AdministatorUser}
import com.miloszjakubanis.crypticcommand.articles.SimpleArticle
import com.miloszjakubanis.crypticcommand.users.privilege.{RemoveUserFunction, AddArticleFunction}

@main def hello =
  val factory = SimpleUserFactory()
  val admin = factory.createAdmin("og_pixel")
  val user1 = factory.createUser("pixel")
  val user2 = factory.createUser("Daniel")

  Array(admin, user1, user2).foreach(e => 
  println(
  s"""|ID: ${e.userID}
      |Name: ${e.userName}
  """.stripMargin))

  val z = user1(AddArticleFunction, SimpleArticle("HELLO"))
  user1.storage().foreach((k,v) => {
    println(k)
    println(v.content)
  })

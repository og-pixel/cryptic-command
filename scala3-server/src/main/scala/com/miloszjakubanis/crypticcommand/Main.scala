package com.miloszjakubanis.crypticcommand
import com.miloszjakubanis.crypticcommand.users.factory.SimpleUserFactory
import com.miloszjakubanis.crypticcommand.users.{User, AdministatorUser}
import com.miloszjakubanis.crypticcommand.articles.SimpleArticle
import com.miloszjakubanis.crypticcommand.users.functions.{RemoveUserFunction, AddArticleFunction}
import com.miloszjakubanis.crypticcommand.users.functions.UserFunction
import com.miloszjakubanis.crypticcommand.users.privilege.*
import com.miloszjakubanis.crypticcommand.users.storage.Storage

import javax.lang.model.element.ModuleElement.DirectiveKind

object Main:
  def main(args: Array[String]) =
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
  
    user1(AddArticleFunction, SimpleArticle("Hello World"))
    val p = userSpecial(AddArticleFunction, SimpleArticle("Hello World"))

    user1.storage().foreach((k,v) => {
      println(k)
      println(v.content)
    })
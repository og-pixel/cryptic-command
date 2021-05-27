package com.miloszjakubanis.crypticcommand
import com.miloszjakubanis.crypticcommand.users.factory.SimpleUserFactory
import com.miloszjakubanis.crypticcommand.users.{User, AdministatorUser}
import com.miloszjakubanis.crypticcommand.articles.SimpleArticle
import com.miloszjakubanis.crypticcommand.users.functions.{RemoveUserFunction, AddArticleFunction}
import com.miloszjakubanis.crypticcommand.users.functions.UserFunction
import com.miloszjakubanis.crypticcommand.users.privilege.*
import com.miloszjakubanis.crypticcommand.users.Storage

// import com.miloszjakubanis.crypticcommand.users.functions.CustomUserFunction
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
  
    user1(AddArticleFunction, new SimpleArticle(""))
    // user1(new AddArticleFunction(), SimpleArticle("HELLO"))
    // userSpecial(new AddArticleFunction(), SimpleArticle("HELLO"))
    // userSpecial(AddArticleFunction, SimpleArticle("HELLO"))
  
    // val z = user1.runFunction[String, String](new UserFunction[String, String] {
    //   def apply(arg: String)(using user: User): String = arg
    // }, "hello custom pass")
    // val article1: SimpleArticle = new SimpleArticle("content1")
    // val article2: SimpleArticle = new SimpleArticle("content2")
    // val article3: SimpleArticle = new SimpleArticle("content3")
    // val article4: SimpleArticle = new SimpleArticle("content4")

    val fun: SimpleArticle => String = (a: SimpleArticle) => a.content
      
    val z = user1(AddArticleFunction, SimpleArticle("Hello World"))
    println(z.get.content)
    Storage.is

    // user1.apply(CustomUserFunction(AddArticleFunction()))

    // user1(CustomUserFunction(SimpleArticle)(zzzzz)), new SimpleArticle("Hello"))

  
  
    user1.storage().foreach((k,v) => {
      println(k)
      println(v.content)
    })
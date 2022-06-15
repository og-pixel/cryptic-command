package com.miloszjakubanis.crypticcommand.controller

import com.miloszjakubanis.crypticcommand.external.{ReadableService, RedisService}
import com.miloszjakubanis.crypticcommand.model.UserDAO
import com.miloszjakubanis.crypticcommand.model.article.ArticleDAO
import com.miloszjakubanis.crypticcommand.views
import play.api.Configuration
import play.api.db.Database
import play.api.mvc.{AnyContent, MessagesAbstractController, MessagesControllerComponents, MessagesRequest}

import java.sql.DriverManager
import javax.inject.{Inject, Singleton}
import scala.collection.mutable.ArrayBuffer
import scala.concurrent.ExecutionContext

//TODO this class should register users and log them in
@Singleton
class PocketController @Inject()(
                                  controllerComponents: MessagesControllerComponents,
                                  val config: Configuration,
                                  val readableServer: ReadableService,
                                  val redisService: RedisService,
                                  val db: Database,
                                  implicit val ec: ExecutionContext
) extends MessagesAbstractController(controllerComponents) {

  val mainPostUrl = routes.PocketController.pocketRegisterGet()
  val mainArticleURL = routes.PocketController.pocketIndexGet()

  val users: ArrayBuffer[UserDAO] = ArrayBuffer(
    UserDAO("test-user", "password")
  )

  val articles: ArrayBuffer[ArticleDAO] =
    ArrayBuffer()

  def pocketRegisterGet() = Action { implicit request: MessagesRequest[AnyContent] =>
    println("hello get")
    Ok(views.html.pocketIndex(users.toSeq, UserDAO.userForm, mainPostUrl))
  }

  def pocketRegisterPost() = Action { implicit request: MessagesRequest[AnyContent] =>
//    val conn = DriverManager.getConnection("jdbc:h2:/home/og_pixel/database-file")
    val a = UserDAO.userForm.bindFromRequest()
    println("hello post")
    a.fold(
      e => {
        println("failure" + e)
        BadRequest(views.html.pocketIndex(users.toSeq, e, mainPostUrl))
      },
      v => {
        val user = UserDAO(v.name, v.password)
        users += user
        println(users)
        db.withConnection { conn =>

          conn.prepareStatement(
            s"INSERT INTO users (login, password) VALUES ('${v.name}', '${v.password}');"
          ).execute()

//          val statement = conn.createStatement()
//          statement.execute(s"INSERT INTO users (login, password) VALUES ('${v.name}', '${v.password}');")
        }
        Redirect(mainPostUrl) //.flashing("info" -> "User added!")
      }
    )
  }

  def pocketLoginGet() = Action { implicit request: MessagesRequest[AnyContent] =>
    Ok(views.html.pocketIndex(users.toSeq, UserDAO.userForm, mainPostUrl))
  }

  def pocketLoginPost() = Action { implicit request: MessagesRequest[AnyContent] =>
    val a = UserDAO.userForm.bindFromRequest()
    a.fold(
      e => {
        BadRequest(views.html.pocketIndex(users.toSeq, e, mainPostUrl))
      },
      v => {
        val user = UserDAO(v.name, v.password)
        println(s"users: $users")
        println(s"user: $user")
        val res = users.filter(_.equals(user));
        if(res.nonEmpty) {
          println(s"Found: ${res(0).name}")
          Redirect(mainPostUrl).flashing(("sucess", res(0).name))
        }
        else {
          println(s"Failed to login lol")
          Redirect(mainPostUrl).flashing(("failure", "lol"))
        }
      }
    )
  }

  def pocketIndexGet() = Action { implicit request: MessagesRequest[AnyContent] =>
    Ok(views.html.pocketIndex(users.toSeq, UserDAO.userForm, mainPostUrl))
  }
}

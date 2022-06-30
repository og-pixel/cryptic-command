package com.miloszjakubanis.crypticcommand.controller

import com.miloszjakubanis.crypticcommand
import com.miloszjakubanis.crypticcommand.external.{
  DatabaseService,
  ReadableService,
  RedisService
}
import com.miloszjakubanis.crypticcommand.model.User
import com.miloszjakubanis.crypticcommand.model.article.ArticleDAO
import com.miloszjakubanis.crypticcommand.views
import play.api.Configuration
import play.api.db.Database
import play.api.libs.json.Json
import play.api.mvc.{
  AnyContent,
  MessagesAbstractController,
  MessagesControllerComponents,
  MessagesRequest
}

import javax.inject.{Inject, Singleton}
import scala.collection.mutable.ArrayBuffer
import scala.concurrent.ExecutionContext

//TODO this class should register users and log them in
@Singleton
class PocketController @Inject() (
    controllerComponents: MessagesControllerComponents,
    val config: Configuration,
    val readableServer: ReadableService,
    val redisService: RedisService,
    val db: Database,
    val dbService: DatabaseService,
    implicit val ec: ExecutionContext
) extends MessagesAbstractController(controllerComponents) {

  val mainPostUrl = routes.PocketController.pocketRegisterGet()
  val mainArticleURL = routes.PocketController.pocketIndexGet()

  val users: ArrayBuffer[User] = ArrayBuffer()
  val articles: ArrayBuffer[ArticleDAO] = ArrayBuffer()

  def pocketRegisterGet() = Action {
    implicit request: MessagesRequest[AnyContent] =>
      Ok(views.html.pocketIndex(users.toSeq, User.userForm, mainPostUrl))
  }

  def pocketRegisterPost() = Action {
    implicit request: MessagesRequest[AnyContent] =>
      val a = User.userForm.bindFromRequest()
      a.fold(
        e => {
          BadRequest(views.html.pocketIndex(users.toSeq, e, mainPostUrl))
        },
        v => {
          val user = User(v.login, v.password)
          users += user
          dbService.registerUser(user)
//        db.withConnection { conn =>
//          val hashedPassword = crypticcommand.md5HashPassword(v.password)
//          conn.prepareStatement(
//            s"INSERT INTO users (login, password) VALUES ('${v.login}', '$hashedPassword');"
//          ).execute()
//        }

          Redirect(mainPostUrl).flashing("info" -> "User added!")
        }
      )
  }

  def pocketLoginGet() = Action {
    implicit request: MessagesRequest[AnyContent] =>
      Ok(views.html.pocketIndex(users.toSeq, User.userForm, mainPostUrl))
  }

  def pocketLoginPost() = Action {
    implicit request: MessagesRequest[AnyContent] =>
      val a = User.userForm.bindFromRequest()
      a.fold(
        e => {
          BadRequest(views.html.pocketIndex(users.toSeq, e, mainPostUrl))
        },
        user => {
          val res = dbService.loginUser(user)
          if (res) {
            println(user)
            Redirect(mainPostUrl).flashing(("success" -> user.login))
          } else {
            Redirect(mainPostUrl).flashing(("failure", "lol"))
          }
        }
      )
  }

  def pocketIndexGet() = Action {
    implicit request: MessagesRequest[AnyContent] =>
//    println(Json.prettyPrint(Json.toJson(User("milosz", "jakubanis"))))

      Ok(views.html.pocketIndex(users.toSeq, User.userForm, mainPostUrl))
  }
}

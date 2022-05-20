package com.miloszjakubanis.crypticcommand.controller

import com.miloszjakubanis.crypticcommand.external.{ReadableController, RedisServer}
import com.miloszjakubanis.crypticcommand.model.UserDAO
import com.miloszjakubanis.crypticcommand.model.article.ArticleDAO
import com.miloszjakubanis.crypticcommand.views
import play.api.Configuration
import play.api.mvc.{AnyContent, MessagesAbstractController, MessagesControllerComponents, MessagesRequest}

import javax.inject.{Inject, Singleton}
import scala.collection.mutable.ArrayBuffer
import scala.concurrent.ExecutionContext

//TODO this class should register users and log them in
@Singleton
class PocketRegisterController @Inject() (
                                     controllerComponents: MessagesControllerComponents,
                                     val config: Configuration,
                                     val readableServer: ReadableController,
                                     val connection: RedisServer,
                                     implicit val ec: ExecutionContext
                                   ) extends MessagesAbstractController(controllerComponents) {

  val mainPostUrl = routes.PocketRegisterController.registerPage()
  val mainArticleURL = routes.PocketRegisterController.pocket()

  val users: ArrayBuffer[UserDAO] = ArrayBuffer(
    UserDAO("test-user", "password")
  )

  val articles: ArrayBuffer[ArticleDAO] =
    ArrayBuffer()

  def registerPage() = Action { implicit request: MessagesRequest[AnyContent] =>
    Ok(views.html.pocketIndex(users.toSeq, UserDAO.userForm, mainPostUrl))
  }

  def registerPost() = Action { implicit request: MessagesRequest[AnyContent] =>
    val a = UserDAO.userForm.bindFromRequest()
    a.fold(
      e => {
        println("failure" + e)
        BadRequest(views.html.pocketIndex(users.toSeq, e, mainPostUrl))
      },
      v => {
        val user = UserDAO(v.name, v.password)
        users += user
        println(users)
        Redirect(mainPostUrl)//.flashing("info" -> "User added!")
      }
    )
  }

  def pocket() = Action { implicit request: MessagesRequest[AnyContent] =>
    Ok(views.html.pocketIndex(users.toSeq, UserDAO.userForm, mainPostUrl))
  }
}
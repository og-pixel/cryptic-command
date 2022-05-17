package com.miloszjakubanis.crypticcommand.controller

import akka.http.scaladsl.model.HttpHeader.ParsingResult.Ok
import com.miloszjakubanis.crypticcommand.external.{ReadableController, RedisServer}
import com.miloszjakubanis.crypticcommand.model.UserDAO
import play.api.Configuration
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents, MessagesAbstractController, MessagesControllerComponents, MessagesRequest, Request}
import play.api.libs.json.{JsObject, JsResult, JsSuccess, JsValue}
import com.miloszjakubanis.crypticcommand.views
import play.api.data.Form
import com.miloszjakubanis.crypticcommand.controller.routes
import com.miloszjakubanis.crypticcommand.model.article.ArticleDAO

import java.net.URL
import javax.inject.{Inject, Singleton}
import scala.collection.mutable.ArrayBuffer
import scala.concurrent.{ExecutionContext, Future}
import scala.util.Try

//TODO this class should register users and log them in
@Singleton
class RegisterController @Inject() (
                                     controllerComponents: MessagesControllerComponents,
                                     val config: Configuration,
                                     val readableServer: ReadableController,
                                     val connection: RedisServer,
                                     implicit val ec: ExecutionContext
) extends MessagesAbstractController(controllerComponents) {

  val mainPostUrl = routes.RegisterController.registerPage()
  val mainArticleURL = routes.RegisterController.articlePage()

  val users: ArrayBuffer[UserDAO] = ArrayBuffer(
      UserDAO("test-user", "password")
    )

  val articles: ArrayBuffer[ArticleDAO] =
    ArrayBuffer()

  def articlePage() = Action { implicit request: MessagesRequest[AnyContent] =>
    Ok(views.html.getArticlePage(ArticleDAO.articleForm, mainPostUrl))
  }

  def articlePagePost() = Action.async {
    implicit request: MessagesRequest[AnyContent] =>

      ArticleDAO.articleForm.bindFromRequest().fold(
        e => {
          println("article not extracted")
          Future(BadRequest(
            views.html.getArticlePage(ArticleDAO.articleForm, mainArticleURL)
          ))
        },
        v => {
          Try(new URL(v.address)).fold(e => Future(Ok(s"Fail: $e")), a => {
            for {
              article <- readableServer.downloadArticle(a)
              _ <- readableServer.createArticleDirectory(article.title)
              _ <- readableServer.saveArticleImages(article)
              _ <- readableServer.replaceArticleImagesWithLocal(article)
              _ <- readableServer.saveArticleToDatabase(article, article.title)
              path <- readableServer.zipArticle(article)
            } yield Ok.sendFile(path.toFile)
          })
        }
      )
  }

  def loginPage() = Action {
    implicit request: MessagesRequest[AnyContent] =>
    Ok(views.html.loginPage())
  }

  def registerPage() = Action { implicit request: MessagesRequest[AnyContent] =>
    Ok(views.html.registerPage(users.toSeq, UserDAO.userForm, mainPostUrl))
  }

  def registerPost() = Action { implicit request: MessagesRequest[AnyContent] =>
    val a = UserDAO.userForm.bindFromRequest()
    a.fold(
      e => {
        println("failure" + e)
        BadRequest(views.html.registerPage(users.toSeq, e, mainPostUrl))
      },
      v => {
        val user = UserDAO(v.name, v.password)
        users += user
        println(users)
        Redirect(mainPostUrl).flashing("info" -> "User added!")
      }
    )
  }
}

package com.miloszjakubanis.crypticcommand.controller

import com.miloszjakubanis.crypticcommand.external.{
  ReadableController,
  RedisServer
}
import play.api.Configuration
import play.api.mvc.{AnyContent, BaseController, ControllerComponents, Request}
import com.miloszjakubanis.crypticcommand.views

import java.net.URL
import javax.inject.{Inject, Singleton}
import scala.concurrent.ExecutionContext

@Singleton
class IndexController @Inject() (
    val controllerComponents: ControllerComponents,
    val config: Configuration,
    val connection: RedisServer,
    val readableServer: ReadableController,
    implicit val ec: ExecutionContext
) extends BaseController {

  //Debug page
  def index() = Action { implicit request: Request[AnyContent] =>
//    val address = {
//      "https://www.wired.com/story/hours-working-vr-tips/"
//      //        "https://news.microsoft.com/2022/01/18/microsoft-to-acquire-activision-blizzard-to-bring-the-joy-and-community-of-gaming-to-everyone-across-every-device/"
//    }
//    for {
//      article <- readableServer.downloadArticle(new URL(address))
//      _ <- readableServer.createArticleDirectory(article.title)
//      _ <- readableServer.saveArticleImages(article)
//      _ <- readableServer.replaceArticleImagesWithLocal(article)
//      _ <- readableServer.saveArticleToDatabase(article, article.title)
//      path <- readableServer.zipArticle(article)
//    } yield Ok.sendFile(path.toFile)
    Ok(views.html.index())
  }

  def pocket() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.pocketIndex())
  }

}

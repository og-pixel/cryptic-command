package com.miloszjakubanis.crypticcommand.controller

import com.miloszjakubanis.crypticcommand.external.ArticleManipulationService
import play.api.Configuration
import play.api.libs.json.{JsResult, JsSuccess, JsValue}
import play.api.mvc.{
  Action,
  AnyContent,
  BaseController,
  ControllerComponents,
  Request
}

import java.net.URL
import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

//TODO delete this
// or leave just for debugging sake
@Singleton
class RestController @Inject() (
    val controllerComponents: ControllerComponents,
    val config: Configuration,
//    val connection: RedisService,
    val readableServer: ArticleManipulationService,
    implicit val ec: ExecutionContext
) extends BaseController {

  def postIndex(): Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      request.body.asText match {
        case Some(value) =>
          for {
            article <- readableServer.downloadArticle(new URL(value))
            _ <- readableServer.createArticleDirectory(article.title)
            _ <- readableServer.saveArticleImages(article)
            _ <- readableServer.replaceArticleImagesWithLocal(article)
            _ <- readableServer.saveArticleToDatabase(article, article.title)
            path <- readableServer.zipArticle(article)
          } yield Ok.sendFile(path.toFile)
        case None => Future(Ok("Value not Found"))
      }
  }

}

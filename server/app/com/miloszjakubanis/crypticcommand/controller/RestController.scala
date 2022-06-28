package com.miloszjakubanis.crypticcommand.controller

import com.miloszjakubanis.crypticcommand.external.{ReadableService, RedisService}
import play.api.Configuration
import play.api.libs.json.{JsResult, JsSuccess, JsValue}
import play.api.mvc.{AnyContent, BaseController, ControllerComponents, Request}

import java.net.URL
import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class RestController @Inject() (
                                 val controllerComponents: ControllerComponents,
                                 val config: Configuration,
                                 val connection: RedisService,
                                 val readableServer: ReadableService,
                                 implicit val ec: ExecutionContext
) extends BaseController {




//  implicit def reads(json: JsValue): JsResult[Person] = {
//    val symbol = (json \ "name").as[String]
//    val price = (json \ "age").as[Int]
//    JsSuccess(Person(symbol, price))
//  }

  def postIndex() = Action.async { implicit request: Request[AnyContent] =>
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

//  case class Person(name: String, age: Int)
}

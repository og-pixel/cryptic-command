package com.miloszjakubanis.crypticcommand.controller

import com.miloszjakubanis.crypticcommand.external.{ReadableServer, RedisServer}
import com.miloszjakubanis.crypticcommand.model.Article
import net.ruippeixotog.scalascraper.browser.JsoupBrowser
import play.api.Configuration
import play.api.libs.json.{JsObject, JsResult, JsSuccess, JsValue}
import play.api.mvc.{AnyContent, BaseController, ControllerComponents, Request}
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

import scala.xml.parsing.XhtmlParser
import java.io.File
import java.net.URL
import java.nio.file.{CopyOption, Files, Paths, StandardCopyOption}
import javax.inject.{Inject, Singleton}
import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success}

@Singleton
class RestController @Inject() (
                                 val controllerComponents: ControllerComponents,
                                 val config: Configuration,
                                 val connection: RedisServer,
                                 val readableServer: ReadableServer,
                                 implicit val ec: ExecutionContext
) extends BaseController {

  def index() = Action { implicit request: Request[AnyContent] =>
//    connection.redisClient.set("Tom", "[Some value]")
//    val z = connection.redisClient.get("Tom")
//    z match {
//      case Some(value) => Ok("Hello World: " + value)
//      case None        => Ok("Hello World")
//    }
//    Ok(com.miloszjakubanis.crypticcommand.views.html.index())
//      val content = Files.readAllBytes(Paths.get("/home/og_pixel/dotfiles.tar"))
//      println(new String(content))
//      Ok("hello")//.as("application/x-tar")
    val ser = readableServer
    val address = "https://www.bbc.com/culture/article/20220114-the-surprising-ways-that-victorians-flirted"
    val article = ser.downloadArticle(new URL(address))

    article.onComplete {
      case Failure(exception) => ???
      case Success(value) =>
        ser.createArticleDirectory(value.title)
        ser.saveArticle(value, value.title).onComplete {
          case Failure(exception) => ???
          case Success(value2) =>
            ser.saveArticleImages(value).onComplete {
              case Failure(exception) => ???
              case Success(value3) => ser.replaceArticleImagesWithLocal(value).onComplete {
                case Failure(exception) => ???
                case Success(value4) => ser.saveArticle(value4, "Amended - " + value4.title)
              }
            }
        }
    }



//    Files.copy(Paths.get("/home/og_pixel/dotfiles.tar"), Paths.get("/tmp/file.tar"), StandardCopyOption.REPLACE_EXISTING)
//    Ok.sendFile(new java.io.File("/tmp/file.tar"), onClose = onCloseFun)
    Ok("hello")
  }


  //TODO for now this function is in server
//  def downloadImages(article: Article): Unit = {
//
//  }

  implicit def reads(json: JsValue): JsResult[Person] = {
    val symbol = (json \ "name").as[String]
    val price = (json \ "age").as[Int]
    JsSuccess(Person(symbol, price))
  }

  def postIndex() = Action { implicit request: Request[AnyContent] =>
    val json = request.body.asJson.get
    val person = json.as[Person](reads)
    connection.redisClient.set(person.name, person)
    Ok(s"Written object $person")
  }

  def postArticle() = Action { implicit request => //: Request[AnyContent] =>
//    val server = readableServer
//    val res = server.downloadArticle(new URL(request.body.asText.get))
//    res match {
//      case Failure(exception) => Ok(s"Failure:\n  $exception")
//      case Success(value) => {
//        val content = Files.readAllBytes(Paths.get("/home/og_pixel/dotfiles.tar"))
//        Ok(content).as("application/x-tar")
//      }
//    }
    Ok("")
  }

  case class Person(name: String, age: Int)
}

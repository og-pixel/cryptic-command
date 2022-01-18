package com.miloszjakubanis.crypticcommand.controller

import com.miloszjakubanis.crypticcommand.external.{ReadableServer, RedisServer}
import com.miloszjakubanis.crypticcommand.model.Article
import net.ruippeixotog.scalascraper.browser.JsoupBrowser
import play.api.Configuration
import play.api.libs.json.{JsObject, JsResult, JsSuccess, JsValue}
import play.api.mvc.{
  Action,
  AnyContent,
  BaseController,
  ControllerComponents,
  Request
}
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

import scala.xml.parsing.XhtmlParser
import java.util.zip
import java.io.File
import java.net.URL
import java.nio.file.{CopyOption, Files, Paths, StandardCopyOption}
import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

@Singleton
class RestController @Inject() (
    val controllerComponents: ControllerComponents,
    val config: Configuration,
    val connection: RedisServer,
    val readableServer: ReadableServer,
    implicit val ec: ExecutionContext
) extends BaseController {

  def p() = Action.async { implicit request: Request[AnyContent] =>
    Future(Ok(""))
  }

  def index() = Action.async { implicit request: Request[AnyContent] =>

      val ser = readableServer
      val address = {
//        "https://news.microsoft.com/2022/01/18/microsoft-to-acquire-activision-blizzard-to-bring-the-joy-and-community-of-gaming-to-everyone-across-every-device/"
//        "https://www.playframework.com/documentation/2.8.x/ScalaHome"
        "https://www.bbc.com/culture/article/20220114-the-surprising-ways-that-victorians-flirted"
//        "https://www.gamingonlinux.com/2022/01/wii-u-emulator-cemu-plans-to-go-open-source-and-support-linux/"
      }
//      val article = ser.downloadArticle(new URL(address))

      for {
        article <- ser.downloadArticle(new URL(address))
        _ <- ser.createArticleDirectory(article.title)
        _ <- ser.saveArticleImages(article)
        _ <- ser.replaceArticleImagesWithLocal(article)
        _ <- ser.saveArticle(article, article.title)
        path <- ser.zipArticle(article)
      } yield Ok("hello")//Ok.sendFile(path.toFile)

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

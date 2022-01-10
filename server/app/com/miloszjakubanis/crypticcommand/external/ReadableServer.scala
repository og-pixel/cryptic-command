package com.miloszjakubanis.crypticcommand.external

import com.miloszjakubanis.crypticcommand.model.{Article, UserDAO}
import com.miloszjakubanis.thoughtseize.storage.{FSDatabase, Location, LocationPlace}
import com.typesafe.scalalogging.StrictLogging
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import play.api.Configuration

import java.net.URL
import java.nio.file.Paths
import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}
import scala.sys.process._
import scala.util.{Failure, Success, Try}

@Singleton
class ReadableServer @Inject()(val config: Configuration, implicit val ec: ExecutionContext) extends StrictLogging {

  //Extra methods that are necessary inside but not for API
  //I want this (probably) to download images and rewrite document
  object ArticleProcessor {
    //TODO change into try block?
    def createAssetsFolder(namespace: String, articleName: String): Unit = {
      database.createDirectory(namespace, articleName)
      database.createDirectory(Paths.get(namespace).resolve(articleName).resolve("assets"))
    }

    //TODO somehow make sure that it is clear that this function is slow as fuck
    //    def downloadArticle(url: URL): Try[String] = {
    //      try {
    //        val res = (s"curl --silent $url" #| "readable --quiet").!!
    //        Success(res)
    //      } catch {
    //        case e: Exception =>
    //          logger.error(
    //            "An error occurred when downloading a file",
    //            e.getLocalizedMessage
    //          )
    //          Failure(e)
    //      }
    //    }
    def downloadArticle(url: URL): Future[Article] = {
      Future {
        val readable = (s"curl --silent $url" #| "readable --quiet").!!
        val parsed = Jsoup.parse(readable)
        println("DOWNLOADED ARTICLE")

        Article(parsed.title(), url, parsed)
      }
    }

    def downloadAllImages(article: Article) = {
      val z = article.document.select("img")
      z.forEach(e => {
        e.attr("src", "This is a test replacement for text")
        e.attr("data-gif-src", "GIF REPLACEMENT")
        println(e.toString)
      })
    }


    //TODO this should be the interface where user
    // makes requests, not the inner object
    def userRequest(): Unit = {

    }

    val database: FSDatabase = {
      val fsLocation = config.get[String]("fs.location")
      val location = Location.apply(fsLocation, LocationPlace.TMP)
      new FSDatabase(location)
    }

    implicit def stringToDocument(doc: String): Document = Jsoup.parse(doc)

    def getAllImages(document: Document): Elements = document.select("img")

    def createDocumentFolder(user: UserDAO, namespace: String, articleName: String): Try[Unit] = {
      //TODO if user has permissions
      //if(user) then....
      Try {
        ArticleProcessor.createAssetsFolder(namespace, articleName)
        database.createDirectory(namespace, articleName)
      }
    }


    def replaceSources() = {

    }


  }
}
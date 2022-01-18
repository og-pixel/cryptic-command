package com.miloszjakubanis.crypticcommand.external

import com.miloszjakubanis.crypticcommand.model.{Article, UserDAO}
import com.miloszjakubanis.thoughtseize.storage.{FSDatabase, Location, LocationPlace}
import com.typesafe.scalalogging.StrictLogging
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import play.api.Configuration

import java.io.File
import java.net.URL
import java.nio.file.{Files, Path, Paths}
import javax.imageio.ImageIO
import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}
import scala.io.Source
import scala.sys.process._
import scala.util.{Failure, Success, Try}

@Singleton
class ReadableServer @Inject() (
    val config: Configuration,
    implicit val ec: ExecutionContext
) extends StrictLogging {

  val database: FSDatabase = {
    val fsLocation = config.get[String]("fs.location")
    val location = Location.apply(fsLocation)
    new FSDatabase(location)
  }

  //TODO change into try block?
//  def createArticleDirectory(namespace: String, user: UserDAO, articleTitle: String): Unit = {
//    val path = Paths.get(namespace).resolve(user.name).resolve("article").resolve(articleTitle)
//
//    database.createDirectory(path)
//    database.createDirectory(path.resolve("assets"))
//  }

  def createArticleDirectory(articleTitle: String): Future[Unit] = {
    Future {
      val path = Paths.get("articles").resolve(articleTitle)
      database.createDirectory(path)
      database.createDirectory(path.resolve("assets"))
    }
  }

  def zipArticle(article: Article): Future[Path] = {
    Future {
      val location = database.location.get.resolve("articles").resolve(article.title)

      Process(s"zip -rq \"${article.title}.zip\" \"assets/\" \"${article.title}.html\"", location.toFile).!!
//      val a = (s"echo \"$location\"" #&& s"zip -rq \"${article.title}.zip\" \"assets/\" \"${article.title}.html\"")

      location.resolve(article.title + ".zip")
    }
  }

  //TODO somehow make sure that it is clear that this function is slow as fuck
  def downloadArticle(url: URL): Future[Article] = {
    Future {
      val readable = (s"curl --silent $url" #| "readable --quiet").!!
      val parsed = Jsoup.parse(readable)

      Article(parsed.title(), url, parsed)
    }
  }

  def saveArticle(article: Article, articleTitle: String): Future[Unit] = {
    Future(database.writeBytes(Paths.get("articles").resolve(articleTitle), articleTitle + ".html", article.document.toString.getBytes()))
  }

  def saveArticleImages(article: Article): Future[Unit] = {
//    Future {
//      findAllImages(article).onComplete {
//        case Failure(f) => new RuntimeException("TODO this should not happen")
//        case Success(v) => v.forEach(e => {
//          println("hello world")
//          val a = e.attr("src")
//          val image = ImageIO.read(new URL(a))
//          println(database.location().resolve("articles").resolve(article.title).resolve("assets").resolve(new File(a).getName).toString)
//          ImageIO.write(image, "jpg", new File(database.location().resolve("articles").resolve(article.title).resolve("assets").resolve(new File(a).getName).toString))
//        })
//      }


      findAllImages(article).collect(z => z.forEach(e => {
        val a = e.attr("src")
        val image = ImageIO.read(new URL(a))
        ImageIO.write(image, "png", new File(database.location().resolve("articles").resolve(article.title).resolve("assets").resolve(new File(a).getName).toString))
      }))

//    Future {
//      article.document.select("img").forEach(e => {
//        val a = e.attr("src")
//        val image = ImageIO.read(new URL(a))
//        println(s"We download from: $a")
//        println(database.location().resolve("articles").resolve(article.title).resolve("assets").resolve(new File(a).getName).toString)
//        println("SPECIAL DEBUG:    " + database.location().resolve("articles").resolve(article.title).resolve("assets").resolve(new File(a).getName).toString)
//        ImageIO.write(image, "png", new File(database.location().resolve("articles").resolve(article.title).resolve("assets").resolve(new File(a).getName).toString))
//      })
//    }

//    }
  }

  def replaceArticleImagesWithLocal(article: Article): Future[Article] = {
    Future {
      val z = article.document.select("img")
      z.forEach(e => {
        val imgName = e.attr("src")
        e.attr("src", s"assets/${new File(imgName).getName}")
      })
      article
    }
  }

  def findAllImages(article: Article): Future[Elements] = {
    Future(article.document.select("img"))

//    Future {
//      val z = article.document.select("img")
//
//      //TODO delete
//      z.forEach(e => {
//        val a = e.attr("src")
//        //        val z = Source.fromURL(new URL(a)).mkString
//        //        Files.write(Paths.get("/tmp/play-server/img.jpg"), z.getBytes())
//        //        println(a)
//        //        (new URL(a) #> new File(s"/tmp/play-server/$a")).!!
//        //        val src = scala.io.Source.fromURL(a)
////        val image = ImageIO.read(new URL(a))
////        ImageIO.write(image, "jpg", new File(s"/tmp/play-server/${new File(a).getName}"))
//        //        val out = new java.io.FileWriter(s"/tmp/play-server/$i")
//        //        out.write(src.mkString)
//        //        out.close()
//
//
//        //        e.attr("src", "This is a test replacement for text")
//        //        e.attr("data-gif-src", "GIF REPLACEMENT")
//        //        println(e.toString)
//      })
//      z
//    }
  }

//  def createDocumentFolder(
//      user: UserDAO,
//      namespace: String,
//      articleName: String
//  ): Try[Unit] = {
//    //TODO if user has permissions
//    //if(user) then....
//    Try {
//      createArticleDirectory(namespace, user, articleName)
//      database.createDirectory(namespace, articleName)
//    }
//  }

}
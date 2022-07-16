package com.miloszjakubanis.crypticcommand.external

import com.miloszjakubanis.crypticcommand.model.article
import com.miloszjakubanis.crypticcommand.model.article.Article
import com.miloszjakubanis.thoughtseize.storage.{Database, FSDatabase, Location}
import com.typesafe.scalalogging.StrictLogging
import org.jsoup.Jsoup
import org.jsoup.select.Elements
import play.api.Configuration

import java.io.File
import java.net.URL
import java.nio.file.{Path, Paths}
import javax.imageio.ImageIO
import javax.inject.{Inject, Singleton, Named}
import scala.concurrent.{ExecutionContext, Future}
import scala.sys.process._

@Singleton
class ArticleManipulationService @Inject()(
    val config: Configuration,
//    val database: FSDatabase,
    implicit val ec: ExecutionContext,
) extends StrictLogging {

  val database: FSDatabase = {
    val fsLocation = config.get[String]("fs.location")
    val location = Location.apply(fsLocation)
    new FSDatabase(location)
  }


  //TODO article handler
  def zipArticle(article: Article): Future[Path] = {
    Future {
      val location = database.location.get.resolve("articles").resolve(article.title)
      Process(s"zip -rq \"${article.title}.zip\" \"assets/\" \"${article.title}.html\"", location.toFile).!!
      location.resolve(article.title + ".zip")
    }

  }

  //TODO somehow make sure that it is clear that this function is slow as fuck
  //TODO article handler
  def downloadArticle(url: URL): Future[Article] = {
    Future {
      val readable = (s"curl --silent $url" #| "readable --quiet").!!
      val parsed = Jsoup.parse(readable)

      article.Article(parsed.title(), url, parsed)
    }
  }

  //TODO file handler
  def saveArticleToDatabase(article: Article, articleTitle: String, database: Database = database): Future[Unit] = {
    Future {
      database.writeBytes(Paths.get("articles").resolve(articleTitle), articleTitle + ".html", article.document.toString.getBytes())
    }
  }

  //TODO file handler
  def createArticleDirectory(articleTitle: String): Future[Unit] = {
    Future {
      val path = Paths.get("articles").resolve(articleTitle)
      database.createDirectory(path)
      database.createDirectory(path.resolve("assets"))
    }
  }

  def saveArticleImages(article: Article): Future[Unit] = {
      findAllImages(article).collect(z => z.forEach(e => {
        val a = e.attr("src")
        val image = ImageIO.read(new URL(a))
        ImageIO.write(image, "png", new File(database.location().resolve("articles").resolve(article.title).resolve("assets").resolve(new File(a).getName).toString))
      }))
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
  }

}

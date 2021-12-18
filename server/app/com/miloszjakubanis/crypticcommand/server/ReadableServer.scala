package com.miloszjakubanis.crypticcommand.server

import com.miloszjakubanis.flusterstorm.job.Job
import com.miloszjakubanis.thoughtseize.storage.{Database, FSDatabase, Location, LocationPlace}
import com.typesafe.scalalogging.StrictLogging

import java.net.URL
import java.nio.file.Path
import scala.sys.process._
import scala.util.{Failure, Success, Try}
import com.miloszjakubanis.crypticcommand.user.User
import com.miloszjakubanis.crypticcommand.article.Article
import play.api.mvc.{AnyContent, BaseController, ControllerComponents, Request}
import javax.inject.Singleton

import javax.inject.Inject

object ReadableServer extends StrictLogging {

  private[this] val programList = Array(
    "curl -h",
    "readable -h"
  )

  private[server] def ensureProgramsInstalled(): Unit = {
    for (i <- programList.indices) {
      try {
        Seq(s"bash", "-c", programList(i)).!!
        logger.debug("Program: {} exists!", programList(i))
      } catch {
        case e: Exception =>
          logger.error("Program is not installed", e)
        //          throw new RuntimeException("Program is not installed:\n", e)
      }
    }
  }

}

@Singleton
class ReadableServer @Inject() (val controllerComponents: ControllerComponents)
//                               (
//    val database: Database,
//    val serverConfig: ServerConfig
//)
  extends BaseController
    with StrictLogging {

  ReadableServer.ensureProgramsInstalled()

//  val root: Path = database.location()
  val root: Path = new FSDatabase(Location("tmp-1", LocationPlace.TMP)).location()

//  private[this] val portIn = Integer.parseInt(serverConfig.portIn)

  def uuid = java.util.UUID.randomUUID.toString

  //TODO somehow make sure that it is clear that this function is slow as fuck
  def downloadArticle(url: URL): Try[String] = {
    try {
      val res = (s"curl --silent $url" #| "readable --quiet").!!
      Success(res)
    } catch {
      case e: Exception =>
        logger.error(
          "An error occurred when downloading a file",
          e.getLocalizedMessage
        )
        Failure(e)
    }
  }

  def saveArticle(article: Article): Unit = {}

  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(com.miloszjakubanis.crypticcommand.views.html.index())
  }
}

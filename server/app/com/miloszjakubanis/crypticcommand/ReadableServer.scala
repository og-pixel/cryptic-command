package com.miloszjakubanis.crypticcommand

import com.miloszjakubanis.thoughtseize.storage.{FSDatabase, Location, LocationPlace}
import com.typesafe.scalalogging.StrictLogging
import play.api.mvc.{AnyContent, BaseController, ControllerComponents, Request}

import java.net.URL
import java.nio.file.Path
import javax.inject.{Inject, Singleton}
import scala.util.{Failure, Success, Try}
import scala.sys.process._

//object ReadableServer extends StrictLogging {
//
//  private[this] val programList = Array(
//    "curl -h",
//    "readable -h"
//  )
//
//  private[server] def ensureProgramsInstalled(): Unit = {
//    for (i <- programList.indices) {
//      try {
//        Seq(s"bash", "-c", programList(i)).!!
//        logger.debug("Program: {} exists!", programList(i))
//      } catch {
//        case e: Exception =>
//          logger.error("Program is not installed", e)
//        //          throw new RuntimeException("Program is not installed:\n", e)
//      }
//    }
//  }
//
//}

class ReadableServer extends StrictLogging {

//  ReadableServer.ensureProgramsInstalled()

//  val root: Path = new FSDatabase(Location("tmp-1", LocationPlace.TMP)).location()

//  def uuid = java.util.UUID.randomUUID.toString

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

}
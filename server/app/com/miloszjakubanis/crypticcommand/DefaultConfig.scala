package com.miloszjakubanis.crypticcommand

import com.typesafe.scalalogging.StrictLogging
import play.api.Configuration
import scala.sys.process._

import javax.inject.Singleton
import javax.inject.Inject
import scala.collection.immutable.HashMap

@Singleton
class DefaultConfig extends StrictLogging {

  private[this] val programList = Array(
    "curl -h",
    "readable -h"
  )

  private[this] def ensureProgramsInstalled(): Unit = {
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

  ensureProgramsInstalled()

}

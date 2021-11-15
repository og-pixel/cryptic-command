package com.miloszjakubanis

import com.typesafe.scalalogging.StrictLogging

import scala.sys.process.stringSeqToProcess

package object crypticcommand extends StrictLogging {

  private[this] val programList = Array(
    "curl -h",
    "readable -h",
  )

  def ensureProgramsInstalled(): Unit = {
    for(i <- programList.indices){
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

package com.miloszjakubanis
import com.miloszjakubanis.crypticcommand.model.User.Password

import java.math.BigInteger
import java.security.MessageDigest
import com.typesafe.scalalogging.StrictLogging

import scala.sys.process._

package object crypticcommand extends StrictLogging {


  // returns a 32-character MD5 hash version of the input string
  def md5HashPassword(usPassword: String): Password = {
    val md = MessageDigest.getInstance("MD5")
    val digest: Array[Byte] = md.digest(usPassword.getBytes)
    val bigInt = new BigInteger(1, digest)
    val hashedPassword = bigInt.toString(16).trim
    "%1$32s".format(hashedPassword).replace(' ', '0')
  }

  /////////////////////////
  ///  Private Stuff   ////
  /////////////////////////
  private[this] val programList = Array(
    "curl -h",
    "readable -h"
  )

  private[this] def ensureProgramsInstalled(): Unit = {
    for (i <- programList.indices) {
      try {
        Seq(s"bash", "-c", programList(i)).!!
        logger.debug("Program: {} Exists!", programList(i))
      } catch {
        case e: Exception =>
          logger.error("Program is not installed", e)
          throw new RuntimeException("Program is not installed:\n", e)
      }
    }
  }

  ensureProgramsInstalled()
}
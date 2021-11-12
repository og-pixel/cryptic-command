package com.miloszjakubanis.crypticcommand

import java.io.{BufferedReader, InputStreamReader}
import scala.util.{Failure, Success, Try}
import scala.sys.process.Process

//Simpler wrapper around ProcessBuilder to get results easily
object CommandRunner {

  def runCommand(args: String*): Try[String] = {
    try {
      val pb = new ProcessBuilder()

      val arg: Array[String] = Array(args: _*)
      val command = pb.command(arg: _*)
      val res = command.start()
      val reader = new BufferedReader(new InputStreamReader(res.getInputStream))

      var content = ""
      var fullContent = ""
      do {
        val X = reader.readLine()
        content = X
        if (X != null) fullContent += X + "\n"
      } while (content != null)

      Success(fullContent)
    } catch {
      case e: RuntimeException => Failure(e)
    }
  }

  def lol(arg: String): String = {
    val pol = "readable" ++ arg
    val a = Process(pol).!!
    a
  }

}

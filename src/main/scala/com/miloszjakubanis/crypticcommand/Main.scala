package com.miloszjakubanis.crypticcommand

import java.io.File
import scala.sys.process._

object Main extends App {
//  val res = CommandRunner.runCommand("curl", "https://apibakery.com/blog/django-settings-howto/")
//
//
//  val p = res.get
//  val res2 = CommandRunner.lol(p)
//  val res2 = CommandRunner.runCommand("readable", p)


//  val a = Seq("bash", "-c", "curl https://apibakery.com/blog/django-settings-howto/") #| Seq("sh", "-c", "readable", "-S") #> new File("/home/og_pixel/OUTPUT.html")
  val a = Seq("bash", "-c", "curl -s https://www.scala-js.org/doc/tutorial/basic/index.html") #| Seq("sh", "-c", "readable -q") #> new File("/home/og_pixel/OUTPUT.html")
  val res = a.!!
//  println(res)

//  val b = a.lazyLines
//  b.foreach(e => println(e))

}

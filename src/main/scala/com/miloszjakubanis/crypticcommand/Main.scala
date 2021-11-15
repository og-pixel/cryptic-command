package com.miloszjakubanis.crypticcommand

import com.miloszjakubanis.crypticcommand.server.ReadableServer

object Main extends App {

//  try{
//    Files.createDirectory(Paths.get("/data"))
//  }catch {
//    case e: FileAlreadyExistsException => ()
//    case e: Exception => e.printStackTrace()
//  }
//
//  val a = Seq("bash", "-c", "curl -s https://arstechnica.com/gadgets/2021/11/psa-apple-isnt-actually-patching-all-the-security-holes-in-older-versions-of-macos/") #| Seq("sh", "-c", "readable -Cq") #> new File("/data/OUTPUT.html")
//  val res = a.!!
//
//  println(s"written file to $res")
//  val file = Files.readString(Paths.get("/data/OUTPUT.html"))
//  if(!file.isBlank) println("File written")
//  else println("File error")

  val server = new ReadableServer()

}

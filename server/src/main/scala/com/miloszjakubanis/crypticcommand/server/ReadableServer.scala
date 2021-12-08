package com.miloszjakubanis.crypticcommand.server

import com.miloszjakubanis.flusterstorm.job.Job
import com.miloszjakubanis.thoughtseize.storage.Database
import com.typesafe.scalalogging.StrictLogging
import io.netty.bootstrap.ServerBootstrap
import io.netty.channel._
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioServerSocketChannel

import java.net.URL
import java.nio.file.Path
import scala.sys.process._
import scala.util.{Failure, Success, Try}

object ReadableServer extends StrictLogging {

  private[this] val programList = Array(
    "curl -h",
    "readable -h",
  )

  private[server] def ensureProgramsInstalled(): Unit = {
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

}

class ReadableServer(
    val database: Database,
    val serverConfig: ServerConfig
) extends AutoCloseable
    with StrictLogging {

  ReadableServer.ensureProgramsInstalled()

  val root: Path = database.location()

  private[this] val portIn = Integer.parseInt(serverConfig.portIn)

  private[this] val bossGroup: EventLoopGroup = new NioEventLoopGroup()
  private[this] val group: EventLoopGroup = new NioEventLoopGroup()

  private[this] val initialiser: ChannelInitializer[SocketChannel] =
    (ch: SocketChannel) => {
      ch.pipeline()
        .addLast(
          new ServerRequestDecoder(),
          new ServerResponseDataEncoder(),
          new ServerProcessingHandler()
        )
    }

  val channel: Channel = obtainChannel

  override def close(): Unit = {
    group.shutdownGracefully()
    bossGroup.shutdownGracefully()
  }

  def obtainChannel: Channel = {
    val b = new ServerBootstrap()
    b.group(bossGroup, group)
      .channel(classOf[NioServerSocketChannel])
      .childHandler(initialiser)
      .option(ChannelOption.SO_BACKLOG, new java.lang.Integer(128))
      .childOption(ChannelOption.SO_KEEPALIVE, java.lang.Boolean.TRUE)

    val f : ChannelFuture = b.bind(portIn).sync()
    //TODO it does make it not wait
//    f.channel().closeFuture().sync()

    f.channel()
  }

  //TODO somehow make sure that it is clear that this function is slow as fuck
  def downloadArticle(url: URL): Try[String] ={
    try{
      val res = (s"curl --silent $url" #| "readable --quiet").!!
      Success(res)
    } catch {
      case e: Exception =>
        logger.error("An error occurred when downloading a file", e.getLocalizedMessage)
        Failure(e)
    }
  }

  def runJob(job: Job[_, _]): Unit = {

  }

}

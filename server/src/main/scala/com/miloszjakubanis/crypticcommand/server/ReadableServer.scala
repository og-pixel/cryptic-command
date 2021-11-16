package com.miloszjakubanis.crypticcommand.server

import com.miloszjakubanis.thoughtseize.storage.Database
import com.typesafe.scalalogging.StrictLogging
import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioServerSocketChannel
import io.netty.channel._

import java.nio.file.Path

class ReadableServer(
    val database: Database,
    val serverConfig: ServerConfig
) extends AutoCloseable
    with StrictLogging {

  val root: Path = database.location()

  private[this] val portIn = Integer.parseInt(serverConfig.portIn)
  private[this] val portOut = Integer.parseInt(serverConfig.portOut)

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

}

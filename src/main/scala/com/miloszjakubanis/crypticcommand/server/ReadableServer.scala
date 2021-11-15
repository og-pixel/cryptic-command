package com.miloszjakubanis.crypticcommand.server

import com.miloszjakubanis.thoughtseize.storage.Database
import com.typesafe.scalalogging.StrictLogging
import io.netty.bootstrap.ServerBootstrap
import io.netty.buffer.PooledByteBufAllocator
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.{Channel, ChannelInitializer, ChannelOption, EventLoopGroup, WriteBufferWaterMark}
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioServerSocketChannel
import io.netty.handler.logging.{LogLevel, LoggingHandler}

import java.nio.file.Path

class ReadableServer(
    val database: Database,
    val serverConfig: ServerConfig
) extends AutoCloseable
    with StrictLogging {

  private[this] val initialiser: ChannelInitializer[SocketChannel] = ???

  private[this] val portIn = Integer.parseInt(serverConfig.portIn)
  private[this] val portOut = Integer.parseInt(serverConfig.portOut)

  private[this] val bossGroup: EventLoopGroup = new NioEventLoopGroup()
  private[this] val group: EventLoopGroup = new NioEventLoopGroup()

  val root: Path = database.location()

  private def obtainChannel: Channel = {
    val b = new ServerBootstrap()
    b.childOption(ChannelOption.ALLOCATOR,
      //new UnpooledByteBufAllocator(true, false, false)
      new PooledByteBufAllocator(true)
    )
    b.childOption(ChannelOption.WRITE_BUFFER_WATER_MARK,
      new WriteBufferWaterMark((1 << 19) + (1 << 18), 1 << 20))
    b.childOption(ChannelOption.SO_SNDBUF, Integer.valueOf((1 << 20) + (1 << 18)))
    b.childOption(ChannelOption.SO_RCVBUF, Integer.valueOf(1 << 20))
    b.childOption(ChannelOption.TCP_NODELAY, java.lang.Boolean.TRUE)
    b.childOption(ChannelOption.SO_KEEPALIVE, java.lang.Boolean.TRUE)
    //b.childOption(ChannelOption.AUTO_CLOSE, java.lang.Boolean.TRUE) //true is default
    b.group(bossGroup, group)
      .channel(classOf[NioServerSocketChannel])
      .handler(new LoggingHandler(LogLevel.TRACE))
      .childHandler(initialiser)
    b.bind(portIn).sync.channel
  }

  val channel: Channel = obtainChannel

  override def close(): Unit = {

  }


}

package com.miloszjakubanis.crypticcommand.server

import com.miloszjakubanis.crypticcommand.record.ResponseRecord
import io.netty.channel.{ChannelFutureListener, ChannelHandlerContext, ChannelInboundHandlerAdapter}

class ServerProcessingHandler extends ChannelInboundHandlerAdapter {
  @throws[Exception]
  override def channelRead(ctx: ChannelHandlerContext, msg: Any): Unit = {
    val requestData = msg.asInstanceOf[ResponseRecord[String]]
    val responseData = new ResponseRecord[String](1, "4252")

//    responseData.setIntValue(requestData.getIntValue * 2)

    val future = ctx.writeAndFlush(responseData)
    future.addListener(ChannelFutureListener.CLOSE)
    System.out.println(requestData)
  }
}

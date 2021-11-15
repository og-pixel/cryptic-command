package com.miloszjakubanis.crypticcommand.server

import com.miloszjakubanis.crypticcommand.record.ResponseRecord
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToByteEncoder

class ServerResponseDataEncoder extends MessageToByteEncoder[ResponseRecord[String]] {
  @throws[Exception]
  override protected def encode(ctx: ChannelHandlerContext, msg: ResponseRecord[String], out: ByteBuf): Unit = {
    out.writeInt(msg.key)
  }
}

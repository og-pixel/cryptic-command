package com.miloszjakubanis.crypticcommand.server

import com.miloszjakubanis.crypticcommand.record.ResponseRecord
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ReplayingDecoder

import java.nio.charset.Charset
import java.util

class ServerRequestDecoder extends ReplayingDecoder[ResponseRecord[String]] {
  final private val charset = Charset.forName("UTF-8")

  @throws[Exception]
  protected def decode(ctx: ChannelHandlerContext, in: ByteBuf, out: util.List[AnyRef]): Unit = {
    val int = in.readInt
    val strLen = in.readInt
    val str = in.readCharSequence(strLen, charset).toString
    val data = new ResponseRecord[String](int, str)
    out.add(data)
  }

}

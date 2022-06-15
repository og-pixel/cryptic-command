package com.miloszjakubanis.crypticcommand.external

import com.redis.RedisClient
import play.api.Configuration

import javax.inject.{Inject, Singleton}

@Singleton
class RedisService @Inject()(private[this] val c: Configuration) {

  val redisClient = new RedisClient(
    c.get[String]("redis.host"),
    c.get[Int]("redis.port")
  )
}

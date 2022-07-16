//package com.miloszjakubanis.crypticcommand.external
//
//import com.redis.RedisClient
//import play.api.Configuration
//
//import javax.inject.{Inject, Singleton}

//@Singleton
//class RedisService @Inject() (
//    private[this] val config: Configuration
//) {
//
//  val redisClient = new RedisClient(
//    config.get[String]("redis.host"),
//    config.get[Int]("redis.port")
//  )
//}

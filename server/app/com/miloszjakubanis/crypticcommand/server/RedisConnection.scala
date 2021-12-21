package com.miloszjakubanis.crypticcommand.server

import com.redis.RedisClient

object RedisConnection {

  val LOCATIONS = Array(
    "tmp",
    "",
    "",
  )

  val redis = new RedisClient()

}

package com.miloszjakubanis.crypticcommand.server

import com.typesafe.config.Config

class ServerConfig(c: Config) {
  protected[server] val portIn: String = c.getString("port.in")
  protected[server] val portOut: String = c.getString("port.out")
}

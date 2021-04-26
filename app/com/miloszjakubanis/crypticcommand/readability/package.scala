package com.miloszjakubanis.crypticcommand

package object readability {

  val PRODUCTION = false

  val PACKAGE_NAME = "readability"
  val SERVER_PORT = 3000

  val SERVER_URL = if(PRODUCTION) s"http://$PACKAGE_NAME:$SERVER_PORT/" else s"http://localhost:$SERVER_PORT/"

}

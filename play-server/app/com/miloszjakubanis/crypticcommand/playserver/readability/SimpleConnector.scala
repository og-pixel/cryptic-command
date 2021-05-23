package com.miloszjakubanis.crypticcommand.playserver.readability

class SimpleConnector extends Connector {

  def getArticle(weblink: String): String = {
    get(s"$SERVER_URL?page=$weblink")
  }


}
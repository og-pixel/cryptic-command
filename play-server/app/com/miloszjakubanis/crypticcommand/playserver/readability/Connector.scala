package com.miloszjakubanis.crypticcommand.playserver.readability

import java.net.{HttpURLConnection, URL}
import scala.io.Source
import scala.language.implicitConversions

sealed trait ConnectorState
case object Connecting extends ConnectorState
case object Connected extends ConnectorState
case object Disconnected extends ConnectorState
case object Error extends ConnectorState

trait Connector {

  private[this] implicit def urlToHttp(url: URL): HttpURLConnection = url.openConnection().asInstanceOf[HttpURLConnection]

  def connectionState: ConnectorState = {
//    val response = new URL(SERVER_URL).getResponseCode
//    if(response == HttpURLConnection.HTTP_OK) Connected
//    else if (response == HttpURLConnection.HTTP_BAD_REQUEST) Error
//    else Disconnected
    Connected
  }

  @throws(classOf[java.io.IOException])
  @throws(classOf[java.net.SocketTimeoutException])
  private[readability] def get(url: String,
          connectTimeout: Int = 5000,
          readTimeout: Int = 5000,
          requestMethod: String = "GET"): String =
  {
    val connection = new URL(url).openConnection.asInstanceOf[HttpURLConnection]
    connection.setConnectTimeout(connectTimeout)
    connection.setReadTimeout(readTimeout)
    connection.setRequestMethod(requestMethod)
    val inputStream = connection.getInputStream
    val content = Source.fromInputStream(inputStream).mkString
    if (inputStream != null) inputStream.close()
    content
  }

}
package com.miloszjakubanis.crypticcommand.articles

class SimpleArticle(var content: String) extends Article[String]:
  private[this] var _title = ""
  override def title: String = _title
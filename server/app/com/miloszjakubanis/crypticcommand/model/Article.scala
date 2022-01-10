package com.miloszjakubanis.crypticcommand.model

import org.jsoup.nodes.Document

import java.net.URL

case class Article(title: String, address: URL, document: Document)

package com.miloszjakubanis.crypticcommand.model.article

import org.jsoup.nodes.Document

import java.net.URL

case class Article(title: String, address: URL, document: Document)

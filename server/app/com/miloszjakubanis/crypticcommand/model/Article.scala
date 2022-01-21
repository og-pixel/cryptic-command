package com.miloszjakubanis.crypticcommand.model

import org.jsoup.nodes.Document
import play.api.data.Form
import play.api.data.Forms.{mapping, nonEmptyText}

import java.net.URL


case class Article(title: String, address: URL, document: Document)

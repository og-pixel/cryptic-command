package com.miloszjakubanis.crypticcommand.articles

import scala.util.CommandLineParser.FromString.given_FromString_Int
import com.miloszjakubanis.crypticcommand.users.factory.SimpleUserFactory

trait Article[T]:
  var content: T
  def title: String
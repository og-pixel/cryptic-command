package com.miloszjakubanis.crypticcommand.articles

import com.miloszjakubanis.crypticcommand.users.factory.SimpleUserFactory
import java.net.URL

trait Article[T]:
  var content: T
  def title: String
package com.miloszjakubanis.crypticcommand.model

import play.api.libs.json.{Reads, Writes}

/**
 * This trait just shows that a Class can be broken down
 * into JSON object
 */
trait Stringable[A] {
  implicit val writes: Writes[A]
  implicit val reads: Reads[A]
}

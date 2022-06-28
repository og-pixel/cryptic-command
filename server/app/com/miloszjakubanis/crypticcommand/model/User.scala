package com.miloszjakubanis.crypticcommand.model

import play.api.data.Form
import play.api.data.Forms.{mapping, nonEmptyText}
import play.api.libs.functional.syntax._
import play.api.libs.json._

object User extends Stringable[User] {

  val userForm: Form[User] = Form(
    mapping(
      "username" -> nonEmptyText,
      "password" -> nonEmptyText
    )(User.apply)(User.unapply)
  )

  implicit val writes: Writes[User] =
    (userDAO: User) =>
      Json.obj(
      "username" -> userDAO.name,
      "password" -> userDAO.password
  )

  implicit val reads: Reads[User] = (
    (JsPath \ "username").read[String] and
      (JsPath \ "password").read[String]
    )(User.apply _)

}

case class User(name: String, password: String)
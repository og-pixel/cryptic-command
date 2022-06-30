package com.miloszjakubanis.crypticcommand.model

import com.miloszjakubanis.crypticcommand.md5HashPassword
import com.miloszjakubanis.crypticcommand.model.User.Password
import play.api.data.Form
import play.api.data.Forms.{mapping, nonEmptyText}
import play.api.libs.functional.syntax._
import play.api.libs.json._

object User extends Stringable[User] {

  type Password = String

  val userForm: Form[User] = Form(
    mapping(
      "username" -> nonEmptyText,
      "password" -> nonEmptyText
    )(User.apply)(User.unapply)
  )

  implicit val writes: Writes[User] =
    (userDAO: User) =>
      Json.obj(
      "username" -> userDAO.login,
      "password" -> userDAO.password
  )

  implicit val reads: Reads[User] = (
    (JsPath \ "username").read[String] and
      (JsPath \ "password").read[String]
    )(User.apply _)


  def apply(login: String, password: String): User = {
    new User(login, md5HashPassword(password))
  }

}

case class User(login: String, password: Password)
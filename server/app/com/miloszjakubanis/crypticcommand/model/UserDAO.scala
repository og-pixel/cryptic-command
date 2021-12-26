package com.miloszjakubanis.crypticcommand.model

import play.api.data.Form
import play.api.data.Forms.{mapping, nonEmptyText, text}

object UserDAO {

  val userForm = Form(
    mapping(
      "username" -> nonEmptyText,
      "password"  -> nonEmptyText
    )(UserDAO.apply)(UserDAO.unapply)
  )
}

case class UserDAO (name: String, password: String) {
}

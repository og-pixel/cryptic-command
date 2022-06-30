package com.miloszjakubanis.crypticcommand.external

import com.miloszjakubanis.crypticcommand
import com.miloszjakubanis.crypticcommand.model.User
import play.api.Configuration
import play.api.db.Database

import javax.inject.{Inject, Singleton}

@Singleton
class DatabaseService @Inject() (
    private[this] val config: Configuration,
    val db: Database
) {

  def registerUser(user: User): Boolean = {
    if(userExists(user)) false
    else {
      db.withConnection { conn =>
//        val hashedPassword = crypticcommand.md5HashPassword(user.password)
        conn.prepareStatement(
          s"INSERT INTO users (login, password) VALUES ('${user.login}', '${user.password}');"
        ).execute()
      }
    }
  }

  def loginUser(user: User): Boolean = {
    //TODO make more useful
    findUser(user).nonEmpty
  }

  private def userExists(user: User): Boolean = {
    findUser(user).nonEmpty
  }

  private def findUser(user: User): Option[User] = {
    db.withConnection { conn =>
      val result = conn.prepareStatement(
        s"SELECT * FROM `users` WHERE login='${user.login}' AND password='${user.password}';"
      ).executeQuery()

      //TODO make sure it actually happens
      //TODO so far so good
      if(result.next()) Option(user)
      else Option.empty
    }
  }
}
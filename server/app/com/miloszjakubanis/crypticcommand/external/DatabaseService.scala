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

  def findUser(user: User): Option[User] = {
    db.withConnection { conn =>
      val result = conn.prepareStatement(
        s"SELECT * FROM `users` WHERE login='milosz';"
      ).executeQuery()

      while(result.next()) {
        val a = result.getInt("id")
        val b = result.getString("login")
        val c = result.getString("password")
        println(s"$a  $b  $c")
      }

      return Option.empty
    }
    return Option.empty
  }

}

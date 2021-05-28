package com.miloszjakubanis.crypticcommand

import utest.{TestSuite, Tests, test}
import com.miloszjakubanis.crypticcommand.users.factory.SimpleUserFactory
import com.miloszjakubanis.crypticcommand.articles.{Article, SimpleArticle}
import com.miloszjakubanis.crypticcommand.users.storage.Storage
import com.miloszjakubanis.crypticcommand.users.*
import java.nio.file.Files

object UserSpec extends TestSuite {

  val tests = Tests{
    test("test1"){
      val factory = SimpleUserFactory()

      val a = factory.createUser("Milosz")
      val b = factory.createUser("Thomas")
      val c = factory.createUser("Patrick")
      val d = factory.createAdmin("Andy")

      assert(a.userName == "Milosz", a.userName)
      assert(a.userID == 1, a.userID)

      assert(b.userName == "Thomas", b.userName)
      assert(b.userID == 2, b.userID)

      assert(c.userName == "Patrick", c.userName)
      assert(c.userID == 3, c.userID)

      assert(d.userName == "Andy", d.userName)
      assert(d.userID == 4, d.userID)

      assert(GuestUser.storage == null, GuestUser.storage)
      assert(GuestUser.userID == -1, GuestUser.userID)
    }
    test("Storage Exists"){
      assert(Files.isDirectory(Storage.homeFolder))
    }
    test("test3"){

    }
  }

}
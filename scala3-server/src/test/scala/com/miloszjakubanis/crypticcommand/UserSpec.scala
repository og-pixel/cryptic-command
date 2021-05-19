package com.miloszjakubanis.crypticcommand

import utest.{TestSuite, Tests, test}
import com.miloszjakubanis.crypticcommand.users.factory.SimpleUserFactory
import com.miloszjakubanis.crypticcommand.articles.{Article, SimpleArticle}
import com.miloszjakubanis.crypticcommand.users.User

object UserSpec extends TestSuite:

  override def tests: Tests = Tests{
    val tests = Tests {
      test("Create Factory and User") {
        val factory = SimpleUserFactory()

        val a = factory.createUser("Milosz")
        val b = factory.createUser("Thoams")
        val c = factory.createUser("Patrick")

        assert(a.userName == "Milosz")
        assert(a.userID == 1)
        assert(a.userID == 2)

        assert(b.userName == "Thomas")
        assert(b.userID == 2)

        assert(c.userName == "Patrick")
        assert(c.userID == 3)
        assert(false)
      }
      test("fail"){
        assert(false)
    }
  }
}
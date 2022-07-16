package scala.com.miloszjakubanis.crypticcommand

import com.miloszjakubanis.crypticcommand.model.User
import com.typesafe.scalalogging.StrictLogging
import utest.{TestSuite, Tests, test}

object UserSpec extends TestSuite with StrictLogging {

  override def tests: Tests = Tests {
    test("Create Users and Check passwords are in correct MD5 Hash") - {
      val hashMap = Map(
        "password1" -> "7c6a180b36896a0a8c02787eeafb0e4c",
        "password2" -> "6cb75f652a9b52798eb6cf2201057c73",
        "password3" -> "819b0643d6b89dc9b579fdfc9094f28e",
        "password4" -> "34cc93ece0ba9e3f6f235d4af979b16c",
      )

      val userList = Seq(
        User("Milosz", "password1") -> "password1",
        User("Milosz278", "password2") -> "password2",
        User("jakubek", "password3") -> "password3",
        User("kaczka", "password4") -> "password4"
      )

      userList.foreach { user =>
        assert(hashMap(user._2) == user._1.password)
      }



    }
  }

}

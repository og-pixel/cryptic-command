package com.miloszjakubanis.crypticcommand.users.functions

import scala.collection.mutable.ArrayBuffer
import com.miloszjakubanis.crypticcommand.users.{User, SimpleUser}
import com.miloszjakubanis.crypticcommand.users.AdministatorUser
import com.miloszjakubanis.crypticcommand.articles.Article

trait UserFunction[Input, Output]:
  def apply(arg: Input)(using User): Output

abstract class UserFunctionDecorator[Input, Output](
  protected val function: UserFunction[Input, Output]
  ) extends UserFunction[Input, Output]:

  def apply(arg: Input)(using User): Output =
    function.apply(arg)
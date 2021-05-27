package com.miloszjakubanis.crypticcommand.users.functions

import com.miloszjakubanis.crypticcommand.users.User 

abstract class UserFunctionDecorator[Input, Output](
  protected val function: UserFunction[Input, Output]
  ) extends UserFunction[Input, Output]:

  def apply(arg: Input)(using User): Output =
    function.apply(arg)
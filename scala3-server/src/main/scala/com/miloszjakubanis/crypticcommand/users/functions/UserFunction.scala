package com.miloszjakubanis.crypticcommand.users.functions

import com.miloszjakubanis.crypticcommand.users.User 

trait UserFunction[Input, Output]:
  def apply(arg: Input)(using User): Output
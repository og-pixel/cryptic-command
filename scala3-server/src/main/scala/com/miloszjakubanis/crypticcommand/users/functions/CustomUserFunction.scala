package com.miloszjakubanis.crypticcommand.users.functions

import com.miloszjakubanis.crypticcommand.users.User 

// class CustomUserFunction[Input, Input2, Output](function: UserFunction[Input, Output])(val fun: Input2 => Output)
//   extends UserFunctionDecorator[Input, Output](function):

//   override def apply(input: (Input, Input2))(using User): Output =
//     val (in1, in2) = input
//     println("this is a decorator part")
//     val a = fun(in2)
//     val b = function(in1)
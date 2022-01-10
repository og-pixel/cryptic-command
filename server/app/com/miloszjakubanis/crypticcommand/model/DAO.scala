package com.miloszjakubanis.crypticcommand.model

object DAO {

  implicit def complexToDAO[A](a: A): DAO[A] = ???

  implicit def DAOToComplex[A](a: DAO[A]): A = ???

}

trait DAO[A] {

}

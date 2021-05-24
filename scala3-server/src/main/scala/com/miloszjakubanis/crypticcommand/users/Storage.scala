package com.miloszjakubanis.crypticcommand.users

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashMap
import scala.collection.mutable.Map

trait Storage[T]:
  val storage: T
  def apply(): T = storage

trait SimpleStorage extends Storage[String]
trait NestedStorage[T] extends Storage[Traversable[T]]

class SimpleNestedStorage[T](
  override val storage: HashMap[String, T] = HashMap[String, T]()
  ) extends Storage[HashMap[String, T]]

class SimpleNestedNodeStorage extends Storage[HashMap[String, Node]]:
  override val storage: HashMap[String, Node] = HashMap()


trait Node

class File extends Node with SimpleStorage:
  override val storage: String = ""

class Directory extends SimpleNestedNodeStorage with Node
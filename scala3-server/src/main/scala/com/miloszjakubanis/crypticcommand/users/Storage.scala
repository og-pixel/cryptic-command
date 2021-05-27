package com.miloszjakubanis.crypticcommand.users

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashMap
import scala.collection.mutable.Map

import java.nio.file.Files
import java.io.IOException
import java.nio.file.Paths

object Storage:
  def is = 
    if Files.isDirectory(Paths.get(System.getProperty("user.home") + "/hommmo")) then true
      else 
        Files.createDirectory(Paths.get(System.getProperty("user.home") + "/hommmo"))
        if Files.isDirectory(Paths.get(System.getProperty("user.home") + "/hommmo")) then true
        else false





trait Storage[T]:
  val storage: T
  def apply(): T = storage

// trait FileStorage[T]:


trait SimpleStorage extends Storage[String]
trait NestedStorage[T] extends Storage[Traversable[T]]

class SimpleNestedStorage[T](
  override val storage: HashMap[String, T] = HashMap[String, T]()
  ) extends Storage[HashMap[String, T]]



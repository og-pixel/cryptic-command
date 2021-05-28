package com.miloszjakubanis.crypticcommand.users.storage

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashMap
import scala.collection.mutable.Map

import java.nio.file.Files
import java.io.IOException
import java.nio.file.Paths

object Storage:

  val homeFolderString = System.getProperty("user.home") + s"/$DEFAULT_HOME_FOLDER"
  val homeFolder = Paths.get(homeFolderString)
  val configFolder = Paths.get(homeFolderString + "/config")
  val logsFolder = Paths.get(homeFolderString + "/logs")
  val usersFolder = Paths.get(homeFolderString + "/users")
  val storageFolder = Paths.get(homeFolderString + "/users/storage")
  val debugFolder = Paths.get(homeFolderString + "/.debug")

  def defaultStorageExists: Unit = 
    Array(
     homeFolder,
     configFolder,
     logsFolder,
     usersFolder,
     storageFolder,
     debugFolder,
     ).foreach(e => {
      if !Files.isDirectory(e) then 
          Files.createDirectory(e)
          if !Files.isDirectory(e) then 
            throw RuntimeException("This should not happen")
    })


trait Storage[T]:
  val storage: T
  def apply(): T = storage

trait SimpleStorage extends Storage[String]
trait NestedStorage[T] extends Storage[Traversable[T]]

class SimpleNestedStorage[T](
  override val storage: HashMap[String, T] = HashMap[String, T]()
  ) extends Storage[HashMap[String, T]]


package com.miloszjakubanis.crypticcommand.model

import java.nio.file.{Files, Path}

/** Idea for this trait is to provide an interface to guarantee certain DAO/object is actually on the Filesystem
  */
trait File {
  val filePath: Path
  val lockPath: Path

  private[this] def fileExists: Boolean =
    Files.exists(filePath)

  private[this] def isLocked: Boolean =
    Files.exists(filePath) && Files.exists(lockPath)

  def lockFile(): Boolean = true

  def unlockFile(): Boolean = true

}

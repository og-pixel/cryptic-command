package com.miloszjakubanis.crypticcommand.server

import com.miloszjakubanis.crypticcommand.user.User
import com.miloszjakubanis.flusterstorm.job.Job

trait UserControl {

  def userJob(user: User, job: Job[_, _]): Unit
  def adminJob(user: User, job: Job[_, _]): Unit
}

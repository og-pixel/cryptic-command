package com.miloszjakubanis.crypticcommand

import scala.concurrent.Future
import javax.inject._
import play.api.inject.ApplicationLifecycle
import com.google.inject.AbstractModule

// This creates an `ApplicationStart` object once at start-up and registers hook for shut-down.
@Singleton
class ApplicationStart @Inject() (lifecycle: ApplicationLifecycle) {
  println("Is this real life?")
  // Shut-down hook
  lifecycle.addStopHook { () =>
    println("Shutdown hook")
    Future.successful(())
  }
  //...
}


class StartModule extends AbstractModule {
  override def configure() = {
    bind(classOf[ApplicationStart]).asEagerSingleton()
  }
}
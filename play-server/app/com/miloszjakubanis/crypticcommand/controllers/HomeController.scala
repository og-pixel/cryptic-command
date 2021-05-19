package com.miloszjakubanis.crypticcommand.controllers

import javax.inject._
import play.api.mvc._

@Singleton
class HomeController @Inject() (cc: ControllerComponents)
    extends AbstractController(cc) {

  println("hello world")

  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(com.miloszjakubanis.crypticcommand.views.html.index())
  }

}

package com.miloszjakubanis.crypticcommand.playserver.controllers

import javax.inject._
import play.api.mvc._
import com.miloszjakubanis.crypticcommand.articles.SimpleArticle

@Singleton
class HomeController @Inject() (cc: ControllerComponents)
    extends AbstractController(cc) {

  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(com.miloszjakubanis.crypticcommand.playserver.views.html.index())
  }

}
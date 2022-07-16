package com.miloszjakubanis.crypticcommand.controller

import com.miloszjakubanis.crypticcommand.views
import play.api.mvc.{
  Action,
  AnyContent,
  BaseController,
  ControllerComponents,
  Request
}

import javax.inject.{Inject, Singleton}

@Singleton
class IndexController @Inject() (
    val controllerComponents: ControllerComponents
) extends BaseController {

  def indexGet(): Action[AnyContent] = Action {
    implicit request: Request[AnyContent] =>
      Ok(views.html.index())
  }
}

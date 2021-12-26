package com.miloszjakubanis.crypticcommand.controller

import akka.http.scaladsl.model.HttpHeader.ParsingResult.Ok
import com.miloszjakubanis.crypticcommand.external.Redis
import com.miloszjakubanis.crypticcommand.model.UserDAO
import play.api.Configuration
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents, MessagesAbstractController, MessagesControllerComponents, MessagesRequest, Request}
import play.api.libs.json.{JsObject, JsResult, JsSuccess, JsValue}
import com.miloszjakubanis.crypticcommand.views
import play.api.data.Form
import com.miloszjakubanis.crypticcommand.controller.routes


import javax.inject.{Inject, Singleton}
import scala.collection.mutable.ArrayBuffer

@Singleton
class RegisterController @Inject() (
    controllerComponents: MessagesControllerComponents,
    val config: Configuration,
    val connection: Redis
) extends MessagesAbstractController(controllerComponents) {

  val mainPostUrl = routes.RegisterController.registerPage()

  val users: ArrayBuffer[UserDAO] = {
    ArrayBuffer(
      UserDAO("test-user", "password")
    )
  }

  def registerPage() = Action { implicit request: MessagesRequest[AnyContent] =>
    Ok(views.html.registerPage(users.toSeq, UserDAO.userForm, mainPostUrl))
  }

  def registerPost() = Action {
    implicit request: MessagesRequest[AnyContent] =>
      val a = UserDAO.userForm.bindFromRequest()
      a.fold(e => {
        println("failure" + e)
        BadRequest(views.html.registerPage(users.toSeq, e, mainPostUrl))
      }, v => {
        val user = UserDAO(v.name, v.password)
        users += user
        println("success")
        Redirect(mainPostUrl).flashing("info" -> "User added!")
      })
  }
}

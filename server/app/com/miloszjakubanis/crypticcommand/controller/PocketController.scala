package com.miloszjakubanis.crypticcommand.controller

import com.miloszjakubanis.crypticcommand.external.{DatabaseService, ArticleManipulationService}
import com.miloszjakubanis.crypticcommand.model.User
import com.miloszjakubanis.crypticcommand.views
import play.api.Configuration
import play.api.db.Database
import play.api.mvc.{Action, AnyContent, Call, MessagesAbstractController, MessagesControllerComponents, MessagesRequest}

import javax.inject.{Inject, Singleton}
import scala.concurrent.ExecutionContext

@Singleton
class PocketController @Inject() (
    controllerComponents: MessagesControllerComponents,
    val config: Configuration,
    val dbService: DatabaseService,
    implicit val ec: ExecutionContext
) extends MessagesAbstractController(controllerComponents) {

  val mainPostUrl: Call = routes.PocketController.pocketIndexGet()

  def pocketRegisterPost(): Action[AnyContent] = Action {
    implicit request: MessagesRequest[AnyContent] =>
      val a = User.userForm.bindFromRequest()
      a.fold(
        err => {
          BadRequest(views.html.pocketIndex(err, mainPostUrl))
        },
        value => {
          dbService.registerUser(value)
          Redirect(mainPostUrl).flashing("info" -> "User added!")
        }
      )
  }

  def pocketLoginPost(): Action[AnyContent] = Action {
    implicit request: MessagesRequest[AnyContent] =>
      val res = User.userForm.bindFromRequest()
      res.fold(
        err => {
          BadRequest(views.html.pocketIndex(err, mainPostUrl))
        },
        user => {
          val res = dbService.loginUser(user)
          if (res) {
            Redirect(mainPostUrl).flashing(("success" -> user.login))
          } else {
            Redirect(mainPostUrl).flashing(("failure", "lol"))
          }
        }
      )
  }

  def pocketIndexGet(): Action[AnyContent] = Action {
    implicit request: MessagesRequest[AnyContent] =>
      Ok(views.html.pocketIndex(User.userForm, mainPostUrl))
  }
}

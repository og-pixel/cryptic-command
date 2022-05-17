package com.miloszjakubanis.crypticcommand.model.article

import play.api.data.Form
import play.api.data.Forms.{mapping, nonEmptyText}

//@Singleton
//class ArticleDAOException @Inject() (implicit val ec: ExecutionContext)
//    extends HttpErrorHandler {
//
//  override def onClientError(
//      request: RequestHeader,
//      statusCode: Int,
//      message: String
//  ): Future[Result] = Future.successful(
//    Status(statusCode)("You have failed to provide correct address")
//  )
//
//  override def onServerError(
//      request: RequestHeader,
//      exception: Throwable
//  ): Future[Result] = Future.successful(
//    InternalServerError("Client has failed to provide correct address")
//  )
//}

object ArticleDAO {
  val articleForm: Form[ArticleDAO] = Form(
    mapping(
      "websiteLink" -> nonEmptyText
    )(ArticleDAO.apply)(ArticleDAO.unapply)
  )

//  def unapply(article: ArticleDAO): Option[String] = Some(
//    article.address.toString
//  )
//
//  def apply(address: String): ArticleDAO = {
//    ArticleDAO(new URL(address))
//  }
}

case class ArticleDAO(address: String) {}

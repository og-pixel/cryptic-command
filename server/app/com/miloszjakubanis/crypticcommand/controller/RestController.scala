package com.miloszjakubanis.crypticcommand.controller

import com.miloszjakubanis.crypticcommand.external.Redis
import play.api.Configuration
import play.api.libs.json.{JsObject, JsResult, JsSuccess, JsValue}
import play.api.mvc.{AnyContent, BaseController, ControllerComponents, Request}

import javax.inject.{Inject, Singleton}


@Singleton
class RestController @Inject() (
    val controllerComponents: ControllerComponents,
    val config: Configuration,
    val connection: Redis
) extends BaseController {

  def index() = Action { implicit request: Request[AnyContent] =>
//    connection.redisClient.set("Tom", "[Some value]")
//    val z = connection.redisClient.get("Tom")
//    z match {
//      case Some(value) => Ok("Hello World: " + value)
//      case None        => Ok("Hello World")
//    }
    Ok(com.miloszjakubanis.crypticcommand.views.html.index())
  }

  implicit def reads(json: JsValue): JsResult[Person] = {
    val symbol = (json \ "name").as[String]
    val price = (json \ "age").as[Int]
    JsSuccess(Person(symbol, price))
  }

  def postIndex() = Action { implicit request: Request[AnyContent] =>
    val json = request.body.asJson.get
    val person = json.as[Person](reads)
    connection.redisClient.set(person.name, person)
    Ok(s"Written object $person")
  }

  case class Person(name: String, age: Int)

}

package controllers

import javax.inject.{Inject, Singleton}
import play.api.mvc.{AbstractController, ControllerComponents}
import play.api.libs.json.Json
import play.modules.reactivemongo._
import reactivemongo.bson.BSONObjectID

class ApiController @Inject() (
  components: ControllerComponents,
  val reactiveMongoApi: ReactiveMongoApi
) extends AbstractController(components)
  with MongoController 
  with ReactiveMongoComponents {

  def ping = Action { implicit request => 
    Ok(Json.obj(
      "hello" -> "world",
      "ping" -> true
    ))
  }
}

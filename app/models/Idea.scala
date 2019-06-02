package models

import javax.inject.Inject

import scala.concurrent.{ ExecutionContext, Future }
import play.api.libs.json.{ Json, OFormat }
import reactivemongo.bson.BSONObjectID
import reactivemongo.play.json._

case class Idea(
  _id: Option[BSONObjectID], 
  title: String, 
  description: Option[String])

object Idea {
  implicit val format: OFormat[Idea] = Json.format[Idea]
}
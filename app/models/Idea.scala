package models

import javax.inject.Inject
import java.sql.Date
import play.api.libs.json.{Json, Format}

case class Idea(
  id: Option[Int], 
  title: String, 
  description: Option[String],
  created_date: Date)

object Idea {
  implicit val format: Format[Idea] = Json.format[Idea]
}
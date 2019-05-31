package models

import play.api.libs.json.Json

case class Idea(_id: String, title: String, description: String)

object Idea {
  implicit val format = Json.format[Idea]
}
package models

import play.api.libs.json.Json

case class Idea(id: String, title: String, description: String)

object Idea {
  implicit val format = Json.format[Idea]
}
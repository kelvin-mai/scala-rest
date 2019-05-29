package models

import play.api.libs.json._

case class User(id: String, username: String, password: String)

object User {
  implicit val userWrites: Writes[User] = Writes { user =>
    Json.obj(
      "id" -> user.id,
      "username" -> user.username,
    )
  }
}
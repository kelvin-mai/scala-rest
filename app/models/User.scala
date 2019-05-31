package models

import play.api.libs.json._
import reactivemongo.bson._

case class User(
  _id: String,
  username: String, 
  password: String)

object User {
  implicit val userWrites: Writes[User] = Writes { user =>
    Json.obj(
      "id" -> user._id,
      "username" -> user.username,
    )
  }
}
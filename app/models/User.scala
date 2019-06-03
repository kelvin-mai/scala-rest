package models

import javax.inject.Inject
import play.api.libs.json._
import java.sql.Date

case class User(
  id: Int,
  username: String, 
  password: String,
  created_date: Date)

object User {
  implicit val userWrites: Writes[User] = Writes { user =>
    Json.obj(
      "id" -> user.id,
      "username" -> user.username,
      "created_date" -> user.created_date)
  }
}
package models

import play.api.libs.json.{Json, Writes}
import reactivemongo.bson.{BSONObjectID, BSONDocument, BSONDocumentReader, BSONDocumentWriter}
import reactivemongo.play.json._

case class User(
  _id: Option[BSONObjectID],
  username: String, 
  password: String)

object User {
 implicit object UserWriter extends BSONDocumentWriter[User]{
    def write(user:User):BSONDocument = BSONDocument(
      "_id" -> user._id.getOrElse(BSONObjectID.generate),
      "username" -> user.username,
      "password" -> user.password
    )
  }

  implicit object UserReader extends BSONDocumentReader[User]{
    def read(doc:BSONDocument):User = User(
      doc.getAs[BSONObjectID]("_id"),
      doc.getAs[String]("username").getOrElse(""),
      doc.getAs[String]("passwoord").getOrElse("")
    )
  } 
}
package models

import javax.inject.Inject

import scala.concurrent.{ ExecutionContext, Future }
import play.api.libs.json._
import reactivemongo.api._
import reactivemongo.api.commands._
import reactivemongo.bson._
import reactivemongo.play.json._
import reactivemongo.play.json.collection.JSONCollection
import play.modules.reactivemongo.ReactiveMongoApi

case class Idea(
  _id: Option[BSONObjectID], 
  title: String, 
  description: Option[String])

object Idea {
  implicit val format: OFormat[Idea] = Json.format[Idea]
}

class IdeaRepository @Inject()(implicit ec: ExecutionContext, reactiveMongoApi: ReactiveMongoApi){
  private def collection: Future[JSONCollection] = 
    reactiveMongoApi.database.map(_.collection("ideas"))
  
  def list(limit: Int = 100): Future[Seq[Idea]] = 
    collection.flatMap(_
      .find(BSONDocument())
      .cursor[Idea](ReadPreference.primary)
      .collect[Seq](limit, Cursor.FailOnError[Seq[Idea]]()))

  def create(idea: Idea): Future[WriteResult] =
    collection.flatMap(_.insert(idea))

  def read(id: BSONObjectID): Future[Option[Idea]] = 
    collection.flatMap(_
      .find(BSONDocument("_id" -> id))
      .one[Idea])
  
  def update(id: BSONObjectID, idea: Idea): Future[Option[Idea]] = 
    collection.flatMap(_
      .findAndUpdate(BSONDocument("_id" -> id),
        BSONDocument(
          f"$$set" -> BSONDocument(
            "title" -> idea.title,
            "description" -> idea.description)),
        true)
      .map(_.result[Idea]))
  
  def destroy(id: BSONObjectID): Future[Option[Idea]] =
    collection.flatMap(_
      .findAndRemove(BSONDocument("_id" -> id))
      .map(_.result[Idea]))
}
// package repositories

// import javax.inject.Inject
// import scala.concurrent.{ ExecutionContext, Future }
// import reactivemongo.api.{ Collection, ReadPreference, Cursor }
// import reactivemongo.api.commands.{ WriteResult }
// import reactivemongo.bson.{ BSONDocument, BSONObjectID }
// import reactivemongo.play.json._
// import reactivemongo.play.json.collection.JSONCollection
// import play.modules.reactivemongo.ReactiveMongoApi
// import models.Idea

// class IdeaRepository @Inject()(
//   implicit ec: ExecutionContext, 
//   reactiveMongoApi: ReactiveMongoApi
// ){
//   private def collection: Future[JSONCollection] = 
//     reactiveMongoApi.database.map(_.collection("ideas"))
  
//   def list(limit: Int = 100): Future[Seq[Idea]] = 
//     collection.flatMap(_
//       .find(BSONDocument())
//       .cursor[Idea](ReadPreference.primary)
//       .collect[Seq](limit, Cursor.FailOnError[Seq[Idea]]()))

//   def create(idea: Idea): Future[WriteResult] =
//     collection.flatMap(_.insert(idea))

//   def read(id: BSONObjectID): Future[Option[Idea]] = 
//     collection.flatMap(_
//       .find(BSONDocument("_id" -> id))
//       .one[Idea])
  
//   def update(id: BSONObjectID, idea: Idea): Future[Option[Idea]] = 
//     collection.flatMap(_
//       .findAndUpdate(BSONDocument("_id" -> id),
//         BSONDocument(
//           f"$$set" -> BSONDocument(
//             "title" -> idea.title,
//             "description" -> idea.description)),
//         true)
//       .map(_.result[Idea]))
  
//   def destroy(id: BSONObjectID): Future[Option[Idea]] =
//     collection.flatMap(_
//       .findAndRemove(BSONDocument("_id" -> id))
//       .map(_.result[Idea]))
// }
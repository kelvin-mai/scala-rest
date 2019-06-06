// package controllers

// import javax.inject.{Inject, Singleton}
// import play.api.mvc.{AbstractController, ControllerComponents}
// import play.api.libs.json.Json
// import scala.concurrent.ExecutionContext.Implicits.global
// import scala.concurrent.Future
// import reactivemongo.bson.BSONObjectID

// import models.Idea
// import repositories.IdeaRepository

// class IdeaController @Inject() (
//   components: ControllerComponents,
//   ideasRepo: IdeaRepository
// ) extends AbstractController(components) {
//   def listIdeas = Action.async {
//     ideasRepo.list().map { ideas =>
//       Ok(Json.toJson(ideas))
//     }
//   }
  
//   def createIdea = Action.async(parse.json) {
//     _.body.validate[Idea].map { idea =>
//       ideasRepo.create(idea).map { _ =>
//         Created
//       }
//     }.getOrElse(Future.successful(BadRequest("Invalid format")))
//   }

//   def readIdea(id: BSONObjectID) = Action.async {
//     ideasRepo.read(id).map { maybeIdea => 
//       maybeIdea.map {idea =>
//         Ok(Json.toJson(idea))
//       }.getOrElse(NotFound)
//     }
//   }

//   def updateIdea(id: BSONObjectID) = Action.async(parse.json) {
//     _.body.validate[Idea].map { idea => 
//       ideasRepo.update(id, idea).map {
//         case Some(idea) => Ok(Json.toJson(idea))
//         case _ => NotFound
//       }
//     }.getOrElse(Future.successful(BadRequest("Invalid Json")))
//   }

//   def deleteIdea(id: BSONObjectID) = Action.async {
//     ideasRepo.destroy(id).map {
//       case Some(idea) => Ok(Json.toJson(idea))
//       case _ => NotFound
//     }
//   }
// }


package controllers

import javax.inject.{Inject, Singleton}
import play.api.mvc.{AbstractController, ControllerComponents}
import play.api.libs.json.Json
import repositories.IdeaRepository

@Singleton
class ApiController @Inject()(
                              cc: ControllerComponents,
                              ideas: IdeaRepository,
                              ) extends AbstractController(cc) {

  def ping = Action { implicit request => 
    Ok("Hello world!")
  }

  def getIdea(ideaId: String) = Action { implicit request =>
    ideas.getIdea(ideaId) map { idea => 
      Ok(Json.toJson(idea))
    } getOrElse NotFound
  }

  def getUser(username: String) = Action { implicit request => 
    ideas.getUser(username) map { user => 
      Ok(Json.toJson(user))
    } getOrElse NotFound
  }
}

package controllers

import javax.inject.{Inject, Singleton}
import play.api.mvc.{AbstractController, ControllerComponents}
import play.api.libs.json.Json
import scala.concurrent.{Future,ExecutionContext}

import models.{ Auth, User }
import repositories.UserRepository

class UserController @Inject() (
  components: ControllerComponents,
  usersRepo: UserRepository
) (implicit ec: ExecutionContext) extends AbstractController(components) {
  def listUsers = Action.async { _ => 
    usersRepo.all().map { users =>
      Ok(Json.toJson(users))
    }
  }

  def register = Action.async(parse.json) { 
    _.body.validate[Auth].map { user => 
      usersRepo.register(user).map { _ =>
        Created
      }
    }.getOrElse(Future.successful(BadRequest("Invalid format")))
  }
}
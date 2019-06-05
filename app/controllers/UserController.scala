package controllers

import javax.inject.{Inject, Singleton}
import play.api.mvc.{AbstractController, ControllerComponents}
import play.api.libs.json.Json
import scala.concurrent.{Future,ExecutionContext}

import models.Idea
import repositories.UserRepository

class UserController @Inject() (
  components: ControllerComponents,
  usersRepo: UserRepository
) (implicit ec: ExecutionContext) extends AbstractController(components) {
  def listUsers = Action.async {
    usersRepo.all().map { users =>
      Ok(Json.toJson(users))
    }
  }
}
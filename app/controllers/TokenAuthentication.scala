package controllers

import play.api.mvc._
import models.User

trait TokenAuthentication { self: Controller =>

  def extractToken(header: String) = {
    header.split("Bearer token=") match {
      case Array(_, token) => Some(token)
      case _               => None
    }
  }

  // def withToken(f: => User => Request[AnyContent] => Result) = Action { implicit request =>
  //   request.headers.get("Authorization") flatMap { header =>
  //     extractToken(header) flatMap { token =>
  //        UserRepository.findbytoken(token) map { user =>
  //          f(user)(request)
  //        }
  //     }
  //   } getOrElse Unauthorized("Invalid token")
  // }
}

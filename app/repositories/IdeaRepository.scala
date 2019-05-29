package repositories

import javax.inject.{Singleton}
import models.{Idea, User}

@Singleton
class IdeaRepository {
  private val ideas = Seq(
    Idea("id1", "phone idea", "an app about phones"),
    Idea("id2", "game idea", "an open world game")
  )

  private val users = Seq(
    User("id1", "kelvin", "password")
  )

  def getIdea(ideaId: String): Option[Idea] = ideas.collectFirst {
    case i if i.id == ideaId => i
  }

  def getUser(username: String): Option[User] = users.collectFirst {
    case u if u.username == username => u
  }
}
package repositories

import javax.inject.Inject
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{ Future }
import java.sql.Date
import slick.jdbc.PostgresProfile.api._
import org.mindrot.jbcrypt.BCrypt
import models.{ Auth, User }

class UserRepository @Inject() (
) () { 
  private val Users = TableQuery[UsersTable]
  val db = Database.forConfig("database")

  private class UsersTable(tag: Tag) extends Table[User](tag, "users") {
    def id = column[Option[Int]]("id", O.PrimaryKey, O.AutoInc)
    def username = column[String]("username")
    def password = column[String]("password")
    def created_date = column[Date]("created_date")
    def * = (id, username, password, created_date) <> ((User.apply _).tupled, User.unapply)
  }

  // for debugging only
  def all(): Future[Seq[User]] = db.run(Users.result)

  def register(user: Auth) = db.run(Users += User.authToUser(
    user.copy(
      password = BCrypt.hashpw(user.password, BCrypt.gensalt(12))
    ))).map { _ => () }

  def login(auth: Auth) = { 
    db.run(Users.filter(u => u.username === auth.username).result.headOption)

    // if (BCrypt.checkpw(user.password, auth.password)) {
    //   Some(user)
    // } else None
  }
}
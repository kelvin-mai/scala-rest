package repositories

import javax.inject.Inject
import scala.concurrent.{ ExecutionContext, Future }
import java.sql.Date
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import slick.jdbc.JdbcProfile
import org.mindrot.jbcrypt.BCrypt
import models.User

class UserRepository @Inject() (
  val dbConfigProvider: DatabaseConfigProvider
) (implicit ec: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._

  private val Users = TableQuery[UsersTable]

  private class UsersTable(tag: Tag) extends Table[User](tag, "users") {
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
    def username = column[String]("username")
    def password = column[String]("password")
    def created_date = column[Date]("created_date")
    def * = (id, username, password, created_date) <> ((User.apply _).tupled, User.unapply)
  }

  // for debugging only
  def all(): Future[Seq[User]] = db.run(Users.result)

  def register(user: User) = db.run(Users += user.copy(
    password = BCrypt.hashpw(user.password, BCrypt.gensalt(12))
  )).map { _ => () }

  def login(username: String, password: String): Option[User] = { 
    val user = db.run(Users.filter(_.username === username))
    if (BCrypt.checkpw(user.password, password)) {
      Some(user)
    } else None
  }
}
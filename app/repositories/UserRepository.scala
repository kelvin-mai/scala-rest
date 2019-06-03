package repositories

import javax.inject.Inject
import scala.concurrent.{ ExecutionContext, Future }
import java.sql.Date
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import slick.jdbc.JdbcProfile
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
    def created_date = column[Date]("joined_date")
    def * = (id, username, password, created_date) <> ((User.apply _).tupled, User.unapply)
  }
}
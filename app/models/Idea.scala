package models

import javax.inject.Inject
import java.sql.Date
import play.api.libs.json.{Json, Format}

case class Idea(
    id: Option[Int],
    title: String,
    description: Option[String],
    created_date: Date
)

object Idea {
  implicit val format: Format[Idea] = Json.format[Idea]

  def fromDTO(dto: IdeaDTO): Idea =
    new Idea(
      dto.id,
      dto.title,
      dto.description,
      new Date(new java.util.Date().getTime())
    )
}

case class IdeaDTO(
    id: Option[Int],
    title: String,
    description: Option[String]
)

object IdeaDTO {
  implicit val format: Format[IdeaDTO] = Json.format[IdeaDTO]
}

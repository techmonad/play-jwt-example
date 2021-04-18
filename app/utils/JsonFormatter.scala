package utils


import model.{Auth, User}
import play.api.libs.json.Json

object JsonFormatter {

  implicit val userFormat = Json.format[User]


  implicit val authFormat = Json.format[Auth]
}

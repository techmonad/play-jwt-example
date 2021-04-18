package controllers

import javax.inject.Inject
import model.User
import org.slf4j.LoggerFactory
import play.api.http.HeaderNames
import play.api.mvc._
import utils.AuthService

import scala.concurrent.{ExecutionContext, Future}


case class UserRequest[A](user: User, token: String, request: Request[A]) extends WrappedRequest[A](request)

class SecureAction @Inject()(bodyParser: BodyParsers.Default, authService: AuthService)(implicit ec: ExecutionContext) extends ActionBuilder[UserRequest, AnyContent] {

  val logger = LoggerFactory.getLogger(this.getClass)

  private val headerTokenRegex = """Bearer (.+?)""".r

  def parser: BodyParser[AnyContent] = bodyParser

  def invokeBlock[A](request: Request[A], block: UserRequest[A] => Future[Result]): Future[Result] =
    extractBearerToken(request) match {
      case Some(token) =>
        authService.validateToken(token) match {
          case Right(user) =>
            block(UserRequest(user, token, request))
          case Left(message) =>
            logger.warn("Invalid Token")
            Future.successful(Results.Unauthorized(message))
        }
      case None =>
        logger.warn("Token is missing")
        Future.successful(Results.Unauthorized("Token is missing"))
    }

  private def extractBearerToken[A](request: Request[A]): Option[String] =
    request.headers.get(HeaderNames.AUTHORIZATION) collect {
      case headerTokenRegex(token) => token
    }

  protected def executionContext: ExecutionContext = ec
}
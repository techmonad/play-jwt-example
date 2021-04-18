package controllers

import javax.inject.Inject
import model.Auth
import org.slf4j.LoggerFactory
import play.api.libs.json.{JsError, JsValue, Json}
import play.api.mvc.{Action, BaseController, ControllerComponents}
import service.UserService
import utils.AuthService
import utils.JsonFormatter._

class TokenController @Inject()(
                                 val controllerComponents: ControllerComponents,
                                 userService: UserService,
                                 authService: AuthService
                               ) extends BaseController {

  val logger = LoggerFactory.getLogger(this.getClass)

  def getToken(): Action[JsValue] = Action(parse.json) { request =>
    logger.info("Request Body: " + request.body)
    request.body.validate[Auth].fold(error => BadRequest(JsError.toJson(error)), { auth =>
      userService.getUser(auth.email) match {
        case Some(user) =>
          val json = Json.toJson(Map("token" -> authService.encodeToken(user)))
          Ok(json)
        case None =>
          Ok("Email does not exist")
      }
    })
  }


}


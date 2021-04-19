package controllers

import javax.inject._
import org.slf4j.LoggerFactory
import play.api.libs.json.Json
import play.api.mvc._
import service.UserService
import utils.JsonFormatter._
import utils.{SecureAction, UserRequest}

@Singleton
class ApiController @Inject()(
                               val controllerComponents: ControllerComponents,
                               secureAction: SecureAction,
                               userService: UserService,
                             ) extends BaseController {

  val logger = LoggerFactory.getLogger(this.getClass)

  def getUserList(): Action[AnyContent] = secureAction { implicit request: UserRequest[AnyContent] =>
    logger.info("Request from user " + request.user)
    val userList = userService.getList()
    Ok(Json.toJson(userList))
  }


}


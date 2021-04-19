package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test._
import play.api.test.Helpers._
import service.UserService
import utils.SecureAction


class ApiControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {

  "HomeController GET" should {

    "get user list without token" in {
      val controller = new ApiController(stubControllerComponents(),inject[SecureAction], inject[UserService])
      val home = controller.getUserList().apply(FakeRequest(GET, "/getUsers"))
      status(home) mustBe UNAUTHORIZED
      contentType(home) mustBe Some("text/plain")
      contentAsString(home) must include("Token is missing")
    }
/*
    "render the index page from the application" in {
      val controller = inject[ApiController]
      val home = controller.index().apply(FakeRequest(GET, "/"))

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include("Welcome to Play")
    }

    "render the index page from the router" in {
      val request = FakeRequest(GET, "/")
      val home = route(app, request).get

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include("Welcome to Play")
    }*/
  }


}

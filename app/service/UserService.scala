package service

import model.User


class UserService {

  private val users = List(User(1, "Bob", "bob@gmail.com"))

  def getUser(email: String): Option[User] = {
    users.find(_.email == email)
  }

  def getList(): List[User] = users
}



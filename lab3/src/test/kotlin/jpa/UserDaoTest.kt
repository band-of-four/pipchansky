package jpa

import org.junit.Test
import User
import RequestResult

class UserDaoTest {
  @Test
  fun insertEmptyUserTest() {
    val userDao = UserDao()
    val user = User().apply {
      username = "admin"
      password = "adminPassword"
      history = ArrayList<RequestResult>().apply { add(RequestResult()) }
    }
    userDao.save(user)
  }
}

package jpa

import org.junit.Test
import User
import RequestResult
import org.junit.Assert.*
import org.junit.Before

class UserDaoTest {
  @Test
  fun insertEmptyUserTest() {
    val userDao = UserDao()
    val user = User()
    user.apply {
      username = "admin"
      password = "adminPassword"
      history = ArrayList<RequestResult>().apply { add(RequestResult().apply { this.user = user }) }
    }
    userDao.save(user)
  }

  @Test
  fun insertOneUserTest() {
    val userDao = UserDao()
    val user = User().apply {
      username = "user1"
      password = "password1"
    }

    user.history = ArrayList<RequestResult>().apply {
      add(RequestResult().apply { x = 1.0; y = 2.0; r = 2.2; isHit = true; this.user = user })
      add(RequestResult().apply { x = 2.0; y = 2.0; r = 1.2; isHit = false; this.user = user })
      add(RequestResult().apply { x = 2.0; y = 3.0; r = 0.2; isHit = true; this.user = user })
    }

    userDao.save(user)
  }

  @Before
  fun findOneUserTest() {
    val userToInsert = User().apply { username = "toInsName"; password = "toInsPassword" }
    userToInsert.history = ArrayList<RequestResult>().apply {
      add(RequestResult().apply { x = 1.0; y = 2.0; r = 2.2; isHit = true; this.user = userToInsert })
      add(RequestResult().apply { x = 2.0; y = 2.0; r = 1.2; isHit = false; this.user = userToInsert })
      add(RequestResult().apply { x = 2.0; y = 3.0; r = 0.2; isHit = true; this.user = userToInsert })
    }

    val userDao = UserDao()
    userDao.save(userToInsert)

    val receivedUser = userDao.findById(1)
    println(receivedUser)

    assertEquals(userToInsert.username, receivedUser?.username)
    assertEquals(userToInsert.password, receivedUser?.password)

    val expectedLst = userToInsert.history as List<RequestResult>
    val actualLst = receivedUser?.history as List<RequestResult>
    for (i in 0..(userToInsert.history?.size as Int - 1)) {
      assertEquals(expectedLst[i].x, actualLst[i].x, 0.001)
      assertEquals(expectedLst[i].y, actualLst[i].y, 0.001)
      assertEquals(expectedLst[i].r, actualLst[i].r, 0.001)
      assertEquals(expectedLst[i].isHit, actualLst[i].isHit)
    }
  }

  @Test
  fun findAllUsersTest() {
    val usr1 = User().apply { username = "findMe1"; password = "findMePass1" }
    usr1.history = ArrayList<RequestResult>().apply {
      add(RequestResult().apply { x = 2.0; y = 21.0; r = 1.223; isHit = false; this.user = usr1 })
      add(RequestResult().apply { x = 11.0; y = 2.0; r = 2.32; isHit = true; this.user = usr1 })
      add(RequestResult().apply { x = 6.0; y = 3.0; r = 0.212; isHit = true; this.user = usr1 })
    }

    val usr2 = User().apply { username = "findMe2"; password = "findMePass2" }
    usr2.history = ArrayList<RequestResult>().apply {
      add(RequestResult().apply { x = 1.4; y = -21.1; r = 1.223; isHit = false; this.user = usr2 })
      add(RequestResult().apply { x = 121.0; y = 2.1; r = 2.212; isHit = true; this.user = usr2 })
      add(RequestResult().apply { x = -1.0; y = 3.32; r = 8.212; isHit = true; this.user = usr2 })
    }

    val userDao = UserDao()
    userDao.save(usr1)
    userDao.save(usr2)

    val receivedUsers = userDao.findAll()
    receivedUsers.forEach {
      println("Username: ${it.username}, Password: ${it.password}, Size: ${it.history?.size}")
    }
  }

  @Test
  fun updateTest() {
    val usr1 = User().apply { username = "updateLogin"; password = "oldPassword" }
    usr1.history = ArrayList<RequestResult>().apply {
      add(RequestResult().apply { x = 2.0; y = 21.0; r = 1.223; isHit = false; this.user = usr1 })
    }

    val userDao = UserDao()
    userDao.save(usr1)
    usr1.password = "AnotherPassword"
    userDao.save(usr1)

    val receivedUser = userDao.findById(usr1.id!!)
    assertEquals(usr1.password, receivedUser?.password)
  }

  @Test
  fun deleteTest() {
    val usr1 = User().apply { username = "unnecessaryLogin"; password = "unnecessaryPassword" }
    usr1.history = ArrayList<RequestResult>().apply {
      add(RequestResult().apply { x = 21.0; y = 81.0; r = 1.243; isHit = true; this.user = usr1 })
    }

    val userDao = UserDao()
    userDao.save(usr1)
    userDao.delete(usr1)

    val receivedUser = userDao.findById(usr1.id!!)
    assertEquals(null, receivedUser)
  }

  @Test
  fun deleteAllTest() {
    val userDao = UserDao()
    val allUsers = userDao.findAll()
    allUsers.forEach {
      userDao.delete(it)
    }
    val receivedUsers = userDao.findAll()
    assertEquals(0, receivedUsers.size)
  }
}

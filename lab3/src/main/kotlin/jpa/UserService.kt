package jpa

import User

/**
 *  @brief This is the class where we can implement necessary business logic.
 *         We should not use the UserDao for that for separating business logic and database access.
 **/
class UserService {
  private val userDao = UserDao()

  fun findUser(id: Long) : User? {
    return userDao.findById(id)
  }

  fun saveUser(user: User): Long? {
    return userDao.save(user)
  }

  fun updateUser(user: User) {
    // We shouldn't use the update method directly for safety
    userDao.saveOrUpdate(user)
  }

  fun deleteUser(user: User) {
    userDao.delete(user)
  }

  fun findAllUsers(): List<User> {
    return userDao.findAll()
  }
}

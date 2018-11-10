package jpa

import User

class UserDao : Dao<User> {
  override fun get(id: Long): User? {
    TODO("not implemented")
  }

  override fun getAll(): List<User> {
    TODO("not implemented")
  }

  override fun save(t: User) {
    TODO("not implemented")
  }

  override fun update(t: User, params: Array<String>) {
    TODO("not implemented")
  }

  override fun delete(t: User) {
    TODO("not implemented")
  }
}
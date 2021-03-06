package ru.b4.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.b4.model.User

interface UserRepository : JpaRepository<User, Long> {

  fun findByUsername(username: String): User?

}

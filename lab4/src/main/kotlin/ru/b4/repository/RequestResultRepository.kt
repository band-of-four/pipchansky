package ru.b4.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.transaction.annotation.Transactional
import ru.b4.model.RequestResult
import ru.b4.model.User

interface RequestResultRepository : JpaRepository<RequestResult, Long> {

  @Transactional
  fun deleteAllByUser(user: User)

  fun findAllByUser(user: User): List<RequestResult>

}

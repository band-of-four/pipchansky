package ru.b4.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.b4.model.RequestResult
import ru.b4.model.User

interface RequestResultRepository : JpaRepository<RequestResult, Long> {

  fun findAllByUser(user: User): List<RequestResult>

}

package ru.b4.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.b4.RequestResult

interface RequestResultRepository : JpaRepository<RequestResult, Long>

package jpa

import java.util.*

interface Dao<T> {

  fun findById(id: Long): T?

  fun findAll(): List<T>

  fun save(t: T)

  fun update(t: T, params: Array<String>)

  fun delete(t: T)

}

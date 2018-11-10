package jpa

import java.util.*

interface Dao<T> {

  fun get(id: Long): T?

  fun getAll(): List<T>

  fun save(t: T)

  fun update(t: T, params: Array<String>)

  fun delete(t: T)

}

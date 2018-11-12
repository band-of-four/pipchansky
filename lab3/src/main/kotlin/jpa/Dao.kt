package jpa

interface Dao<T> {

  fun findById(id: Long): T?

  fun findAll(): List<T>

  fun save(t: T): Long?

  fun update(t: T)

  fun saveOrUpdate(t: T)

  fun delete(t: T)

}

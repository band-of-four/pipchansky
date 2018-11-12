package jpa

import User
import org.hibernate.HibernateException
import org.hibernate.Session
import org.hibernate.Transaction
import javax.persistence.RollbackException

class UserDao : Dao<User> {

  @Throws(HibernateException::class)
  override fun findById(id: Long): User? {
    val session = HibernateUtil.getSessionFactory()?.openSession()
    val response = session?.get(User::class.java, id)
    session?.close()
    return response
  }

  @Throws(HibernateException::class)
  override fun findAll(): List<User> {
    val session = HibernateUtil.getSessionFactory()?.openSession()
    val response = session?.createQuery("from User")?.list()
    session?.close()
    return if (response != null) {
      response as List<User>
    } else {
      emptyList()
    }
  }

  @Throws(HibernateException::class, RollbackException::class)
  override fun save(t: User): Long? {
    var session: Session? = null
    var transaction: Transaction? = null
    try {
      session = HibernateUtil.getSessionFactory()?.openSession()
      transaction = session?.beginTransaction()
      val id = session?.save(t) as Long?
      transaction?.commit()
      return id
    } catch (e: RollbackException) {
      transaction?.rollback()
      throw e
    } finally {
      session?.close()
    }
  }

  @Throws(HibernateException::class, RollbackException::class)
  override fun saveOrUpdate(t: User) {
    var session: Session? = null
    var transaction: Transaction? = null
    try {
      session = HibernateUtil.getSessionFactory()?.openSession()
      transaction = session?.beginTransaction()
      session?.saveOrUpdate(t)
      transaction?.commit()
    } catch (e: RollbackException) {
      transaction?.rollback()
      throw e
    } finally {
      session?.close()
    }
  }

  @Throws(HibernateException::class, RollbackException::class)
  override fun update(t: User) {
    var session: Session? = null
    var transaction: Transaction? = null
    try {
      session = HibernateUtil.getSessionFactory()?.openSession()
      transaction = session?.beginTransaction()
      session?.update(t)
      transaction?.commit()
    } catch (e: RollbackException) {
      transaction?.rollback()
      throw e
    } finally {
      session?.close()
    }
  }

  @Throws(HibernateException::class, RollbackException::class)
  override fun delete(t: User) {
    var session: Session? = null
    var transaction: Transaction? = null
    try {
      session = HibernateUtil.getSessionFactory()?.openSession()
      transaction = session?.beginTransaction()
      session?.delete(t)
      transaction?.commit()
    } catch (e: RollbackException) {
      transaction?.rollback()
      throw e
    } finally {
      session?.close()
    }
  }
}

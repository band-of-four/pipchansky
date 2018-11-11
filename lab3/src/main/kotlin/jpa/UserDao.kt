package jpa

import User
import org.hibernate.HibernateException

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
    val response = session?.createQuery("from User")?.list() as List<User>
    session.close()
    return response
  }

  @Throws(HibernateException::class)
  override fun save(t: User) {
    val session = HibernateUtil.getSessionFactory()?.openSession()
    val transaction = session?.beginTransaction()
    session?.saveOrUpdate(t)
    transaction?.commit()
    session?.close()
  }

  @Throws(HibernateException::class)
  override fun update(t: User) {
    val session = HibernateUtil.getSessionFactory()?.openSession()
    val transaction = session?.beginTransaction()
    session?.update(t)
    transaction?.commit()
    session?.close()
  }

  @Throws(HibernateException::class)
  override fun delete(t: User) {
    val session = HibernateUtil.getSessionFactory()?.openSession()
    val transaction = session?.beginTransaction()
    session?.delete(t)
    transaction?.commit()
    session?.close()
  }
}

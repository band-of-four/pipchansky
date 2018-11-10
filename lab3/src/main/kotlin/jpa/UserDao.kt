package jpa

import User

class UserDao : Dao<User> {
  override fun findById(id: Long): User? {
    return HibernateUtil.getSessionFactory()?.openSession()?.get(User::class.java, id)
  }

  override fun findAll(): List<User> {
    return HibernateUtil.getSessionFactory()?.openSession()?.createQuery("from User")
        ?.list() as List<User>
  }

  override fun save(t: User) {
    val session = HibernateUtil.getSessionFactory()?.openSession()
    val transaction = session?.beginTransaction()
    session?.save(t)
    transaction?.commit()
    session?.close()
  }

  override fun update(t: User, params: Array<String>) {
    val session = HibernateUtil.getSessionFactory()?.openSession()
    val transaction = session?.beginTransaction()
    session?.update(t)
    transaction?.commit()
    session?.close()
  }

  override fun delete(t: User) {
    val session = HibernateUtil.getSessionFactory()?.openSession()
    val transaction = session?.beginTransaction()
    session?.delete(t)
    transaction?.commit()
    session?.close()
  }
}

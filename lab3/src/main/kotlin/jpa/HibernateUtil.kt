package jpa

import org.hibernate.boot.registry.StandardServiceRegistryBuilder
import org.hibernate.SessionFactory
import org.hibernate.cfg.Configuration
import User
import RequestResult


object HibernateUtil {
  private var sessionFactory: SessionFactory? = null

  fun getSessionFactory(): SessionFactory? {
    if (sessionFactory == null) {
      try {
        val configuration = Configuration().configure()
        configuration.addAnnotatedClass(User::class.java)
        configuration.addAnnotatedClass(RequestResult::class.java)
        val builder = StandardServiceRegistryBuilder().applySettings(configuration.properties)
        sessionFactory = configuration.buildSessionFactory(builder.build())
      } catch (e: Exception) {
        e.printStackTrace()
      }
    }
    return sessionFactory
  }
}

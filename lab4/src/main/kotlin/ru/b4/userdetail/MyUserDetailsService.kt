package ru.b4.userdetail

import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.core.userdetails.UserDetails
import ru.b4.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MyUserDetailsService : UserDetailsService {

  @Autowired
  private val userRepository: UserRepository? = null

  override fun loadUserByUsername(username: String): UserDetails {
    val user = userRepository?.findByUsername(username) ?: throw UsernameNotFoundException(username)
    return MyUserPrincipal(user)
  }

}

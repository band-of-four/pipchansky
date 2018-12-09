package ru.b4.userdetail

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import ru.b4.model.User


class MyUserPrincipal(private val user: User) : UserDetails {

  override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
    TODO("not implemented")
  }

  override fun isEnabled() = true

  override fun getUsername() = user.username

  override fun isCredentialsNonExpired() = true

  override fun getPassword() = user.password

  override fun isAccountNonExpired() = true

  override fun isAccountNonLocked() = true

}

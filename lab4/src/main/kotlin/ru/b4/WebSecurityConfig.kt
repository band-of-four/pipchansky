package ru.b4

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
@EnableWebSecurity
class WebSecurityConfig : WebSecurityConfigurerAdapter() {

  @Throws(Exception::class)
  override fun configure(http: HttpSecurity) {
    http
        .authorizeRequests()
          .antMatchers("/js/**", "/index*").permitAll()
          .antMatchers("/graph*").hasRole("USER")
          .and()
        .formLogin()
          .loginPage("/login")
          .loginProcessingUrl("/login")
          .defaultSuccessUrl("/graph",true)
          .failureUrl("/login-error")
          .and()
        .logout().permitAll()
  }

  @Autowired
  @Throws(Exception::class)
  override fun configure(auth: AuthenticationManagerBuilder) {
    auth.inMemoryAuthentication().withUser("user").password("password").roles("USER")
  }

  @Bean
  fun passwordEncoder() = BCryptPasswordEncoder()

}

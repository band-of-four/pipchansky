package ru.b4.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import javax.sql.DataSource

@Configuration
@EnableWebSecurity
class WebSecurityConfig : WebSecurityConfigurerAdapter() {

  @Autowired
  private var dataSource: DataSource? = null

  override fun configure(http: HttpSecurity) {
    http
        .csrf().disable()
        .authorizeRequests()
          .antMatchers("/js/**", "/index*", "/auth/login", "/auth/registration", "/", "/*.ico").permitAll()
          .anyRequest().authenticated()
        .and()
          .formLogin()
          .loginPage("/auth/login")
          .defaultSuccessUrl("/graph", true)
          .permitAll()
        .and()
          .logout().permitAll()
  }


  @Autowired
  override fun configure(auth: AuthenticationManagerBuilder) {
    auth.jdbcAuthentication()
        .dataSource(dataSource)
        .passwordEncoder(NoOpPasswordEncoder.getInstance())
        .usersByUsernameQuery("select username, password, active from usr where username=?")
        .authoritiesByUsernameQuery("select u.username, " +
            "ur.roles from usr u inner join user_role ur on u.id = ur.user_id where u.username=?")
  }

}

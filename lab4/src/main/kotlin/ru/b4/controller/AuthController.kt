package ru.b4.controller

import org.springframework.stereotype.Controller
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import ru.b4.model.Role
import ru.b4.model.User
import ru.b4.repository.UserRepository
import java.lang.RuntimeException
import java.util.*

@Controller
@RequestMapping("/auth")
class AuthController {

  @Autowired
  private val userRepo: UserRepository? = null

  @GetMapping("/login")
  fun login() = "/login.html"

  @GetMapping("/registration")
  fun registration(): String {
    return "/registration.html"
  }

  @PostMapping("/registration")
  @ResponseBody
  fun addUser(user: User): AuthResponse {
    println("=== Registration POST called with \"${user.username}:${user.password}\" ===") // FIXME

    val userFromDb = userRepo?.findByUsername(user.username)
    val authResponse = AuthResponse(user.username, AuthType.REGISTRATION)
    return if (userFromDb != null) {
      authResponse.success = false
      authResponse.message = "Username '${user.username}' is already taken"
      authResponse
    } else {
      user.active = true
      user.roles = Collections.singleton(Role.USER)
      userRepo?.save(user)

      authResponse.success = true
      authResponse.message = "You've successfully registered new account. You can sing up now."
      authResponse
    }
  }

  enum class AuthType { LOGIN, REGISTRATION }

  class AuthResponse(var username: String = "", var type: AuthType = AuthType.LOGIN,
                     var success: Boolean = false, var message: String = "")

}

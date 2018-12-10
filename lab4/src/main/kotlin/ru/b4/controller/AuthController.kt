package ru.b4.controller

import org.springframework.stereotype.Controller
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import ru.b4.model.Role
import ru.b4.model.User
import ru.b4.repository.UserRepository
import java.lang.RuntimeException
import java.util.*
import javax.servlet.http.HttpServletRequest

@Controller
@RequestMapping("/auth")
class AuthController {

  @Autowired
  private val userRepo: UserRepository? = null

  @GetMapping("/login")
  fun login() = "/login.html"

  @ResponseBody
  @PostMapping("/login")
  fun authorize(@RequestParam username: String, @RequestParam password: String): String {
    println("=== Login POST called with $username:$password ===") // FIXME
    if (userRepo == null)
      throw RuntimeException("userRepository is null. @Autowired isn't working!")

    val userFromDb = userRepo.findByUsername(username)
    return if (userFromDb != null && password == userFromDb.password) {
      "You've been authorized! Congratulations!"
    } else {
      "Incorrect login or password :("
    }
  }

  @GetMapping("/registration")
  fun registration(): String {
    return "/registration.html"
  }

  @PostMapping("/registration")
  fun addUser(user: User, model: MutableMap<String, Any>): String {
    println("=== Registration POST called with \"${user.username}:${user.password}\" ===") // FIXME

    if (userRepo == null)
      throw RuntimeException("userRepository is null. @Autowired isn't working!")

    val userFromDb = userRepo.findByUsername(user.username)

    if (userFromDb != null) {
      model["message"] = "User already exists!"
      return "registration.html"
    }

    user.active = true
    user.roles = Collections.singleton(Role.USER)
    userRepo.save(user)

    return "redirect:/login"
  }

}

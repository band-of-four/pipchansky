package ru.b4.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import java.security.Principal

@Controller
class MainController {

  @GetMapping("/username")
  @ResponseBody
  fun currentUserName(principal: Principal): String {
    return principal.name
  }

  @GetMapping("/")
  fun home() = "login.html"

  @GetMapping("/graph")
  fun graph() = "graph.html"

}

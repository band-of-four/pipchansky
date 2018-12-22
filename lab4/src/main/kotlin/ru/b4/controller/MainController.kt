package ru.b4.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class MainController {

  @GetMapping("/")
  fun home() = "login.html"

  @GetMapping("/graph")
  fun graph() = "graph.html"

}

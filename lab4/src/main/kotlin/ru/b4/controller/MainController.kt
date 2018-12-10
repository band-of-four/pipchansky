package ru.b4.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class MainController {

  @GetMapping("/", "/index")
  fun home() = "index.html"

  @GetMapping("/graph")
  fun graph() = "graph.html"

}

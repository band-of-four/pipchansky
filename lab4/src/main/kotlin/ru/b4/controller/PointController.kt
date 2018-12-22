package ru.b4.controller

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import ru.b4.model.Point
import ru.b4.model.User
import java.lang.Math.abs
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestMapping
import ru.b4.repository.RequestResultRepository
import ru.b4.repository.UserRepository
import java.security.Principal


@RestController
@RequestMapping("/point")
class PointController {

  @Autowired
  var userRepository: UserRepository? = null

  @Autowired
  var resultRepository: RequestResultRepository? = null

  @GetMapping("/username")
  fun currentUserName(principal: Principal): String {
    return principal.name
  }

  var points = ArrayList<Point>() // FIXME delete it

  private fun tryHit(x: Double, y: Double, r: Double): Point? {
    if (!validate(x, y, r)) return null
    val hit = checkHit(x, y, r)
    val p = Point(x, y, hit)
    points.add(p)
    return p
  }

  private fun validate(x: Double, y: Double, r: Double) = r in (1.0..4.0) && x in (-4.0..4.0) && y in (-3.0..5.0)

  private fun checkHit(x: Double, y: Double, r: Double): Boolean {
    if (x in -r..0.0 && y in 0.0..r/2) {
      return true
    }
    if (x >= 0 && y >= 0 && x*x + y*y <= r*r/4) {
      return true
    }
    if (x >= 0 && y <= 0 && abs(x) + abs(y) <= r/2) {
      return true
    }
    return false
  }

  @PostMapping
  fun create(@RequestBody p: PointRequest): Point? {
    // TODO add point to db here
    return tryHit(p.x, p.y, p.r)
  }

  @GetMapping("/update/{r}")
  fun getAllRebuilt(@PathVariable r: Double, principal: Principal): List<Point> {
    val user = userRepository?.findByUsername(principal.name)
        ?: throw Exception("User \"${principal.name}\" does not exist!")
    println("=== Called get-all with user: $user ===") // FIXME debug activated
    val reqResults = resultRepository?.findAllByUser(user)
    val points = ArrayList<Point>()
    reqResults?.forEach { points.add(Point(it.x, it.y, checkHit(it.x, it.y, r))) }
    return points
  }

  data class PointRequest(var x: Double = 0.0, var y: Double = 0.0, var r: Double = 0.0)

}

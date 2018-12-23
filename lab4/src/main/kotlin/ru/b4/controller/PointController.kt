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
import ru.b4.model.RequestResult
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

  private fun tryHit(x: Double, y: Double, r: Double): Point? {
    if (!validate(x, y, r)) return null
    val hit = checkHit(x, y, r)
    return Point(x, y, hit)
  }

  private fun validate(x: Double, y: Double, r: Double) = r in (1.0..4.0) && x in (-4.0..4.0) && y in (-3.0..5.0)

  private fun checkHit(x: Double, y: Double, r: Double): Boolean {
    if (x in -r..0.0 && y in 0.0..r / 2) {
      return true
    }
    if (x >= 0 && y >= 0 && x * x + y * y <= r * r / 4) {
      return true
    }
    if (x >= 0 && y <= 0 && abs(x) + abs(y) <= r / 2) {
      return true
    }
    return false
  }

  @PostMapping
  fun create(@RequestBody p: PointRequest, principal: Principal): Point? {
    val point = tryHit(p.x, p.y, p.r) ?: return null

    val user = getUser(principal.name)
    user.history?.add(RequestResult(p.x, p.y, p.r, point.hit).apply { this.user = user }) ?: ArrayList<RequestResult>()
    userRepository?.save(user)

    return point
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

  private fun getUser(username: String) = userRepository?.findByUsername(username)
      ?: throw Exception("User \"$username\" does not exist!")

  @GetMapping("/get-history")
  fun getHistory(principal: Principal): List<HistoryEntry> {
    val user = getUser(principal.name)
    val results = resultRepository?.findAllByUser(user) ?: emptyList()
    val history = ArrayList<HistoryEntry>()
    results.forEach { history.add(HistoryEntry(it.x, it.y, it.r, it.isHit)) }
    return history
  }

  data class PointRequest(var x: Double = 0.0, var y: Double = 0.0, var r: Double = 0.0)

  data class HistoryEntry(var x: Double = 0.0, var y: Double = 0.0, var r: Double = 0.0, var hit: Boolean = false)

}

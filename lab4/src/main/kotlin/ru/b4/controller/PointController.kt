package ru.b4.controller

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import org.springframework.web.bind.annotation.*
import ru.b4.model.Point
import java.lang.Math.abs

@RestController
@RequestMapping("/point")
class PointController {

  var points = ArrayList<Point>()

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
    if (x >= 0 && y >= 0 && x*x + y*y <= r*r/4){
      return true
    }
    if (x >= 0 && y <= 0 && abs(x) + abs(y) <= r/2){
      return true
    }
    return false
  }


  @GetMapping("/{id}")
  fun getOne(@PathVariable id: Int) {

  }

  @PostMapping
  fun create(@RequestBody p: Map<String?, PointRequest>): Point? {
    return tryHit(p["point"]!!.x, p["point"]!!.y, p["point"]!!.r)
  }

  @GetMapping
  fun getAll() {

  }

  @GetMapping("/update/{r}")
  fun getAllRebuilt(@PathVariable r: Double): List<Point> {
    points.map { it.hit = checkHit(it.x, it.y, r) }
    return points
  }

  open class PointRequest(var x: Double = 0.0, var y: Double = 0.0, var r: Double = 0.0)

}

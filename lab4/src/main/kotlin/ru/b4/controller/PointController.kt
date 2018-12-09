package ru.b4.controller

import org.springframework.web.bind.annotation.*
import ru.b4.model.Point

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

  private fun validate(x: Double, y: Double, r: Double) = r in (1..4) && x in (-4..4) && y in (-3..5)

  private fun checkHit(x: Double, y: Double, r: Double): Boolean {
    return true
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

  open class PointRequest() {
    constructor(x: Double, y: Double, r: Double) : this() {
      this.x = x
      this.y = y
      this.r = r
    }

    var x: Double = 0.0
    var y: Double = 0.0
    var r: Double = 0.0
  }

}

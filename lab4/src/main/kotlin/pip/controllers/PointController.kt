package pip.controllers

import org.springframework.web.bind.annotation.*
import pip.Point

@RestController
@RequestMapping("/point")
class PointController {
//    private val history = ArrayList<RequestResult>()
    //    var historyBean: HistoryBean? = null
    var points = ArrayList<Point>()

    private fun tryHit(x: Double, y: Double, r: Double): Point? {
        if (!validate(x, y, r)) return null
        val hit = checkHit(x, y, r)
//        historyBean?.addRequestResult(RequestResult(round(x * 1000) / 1000, round(y * 1000) / 1000, r, hit))
        val p: Point = Point(x, y, hit);
        points.add(p);
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
    fun create(@RequestBody p: Map<Int?, PointRequest>): Point? {
        return tryHit(p[null]!!.x, p[null]!!.y, p[null]!!.r);
    }

    @GetMapping
    fun getAll() {

    }

    @GetMapping("/update/{r}")
    fun getAllRebuilt(@PathVariable r:Double ):List<Point> {
        points.map { it.hit = checkHit(it.x, it.y, r) }
        return points;
    }

    open class PointRequest() {
        constructor(x: Double, y: Double, r: Double) : this() {
            this.x = x;
            this.y = y;
            this.r = r;
        }

        var x: Double = 0.0
        var y: Double = 0.0
        var r: Double = 0.0
    }
}
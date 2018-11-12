import java.io.Serializable
import kotlin.math.*

class AreaCheckBean : Serializable {
  // private val history = ArrayList<RequestResult>()
  var historyBean: HistoryBean? = null
  var x = 0.0
  var y = 0.0
  var r = 1.0

  var hiddenX = 0.0
  var hiddenY = 0.0

  var points = ArrayList<Point>()

  fun tryHit() {
    if (!validate()) return
    val hit = checkHit(x, y, r)
    historyBean?.addRequestResult(RequestResult(round(x*1000)/1000, round(y*1000)/1000, r, hit))
    points.add(Point(x, y, hit))
  }

  fun areaClick() {
    if (!validate()) return
    val hit = checkHit(hiddenX, hiddenY, r)
    historyBean?.addRequestResult(RequestResult(round(hiddenX*1000)/1000, round(hiddenY*1000)/1000, r, hit))
    points.add(Point(hiddenX, hiddenY, hit))
  }

  fun click() {
    points.map { it.hit = checkHit(it.x, it.y, r) }
  }

  // fun getResultsArray() = history.toTypedArray()

  private fun validate() = r in (1..4) && x in (-4..4) && y in (-3..5)

  private fun checkHit(x: Double, y: Double, r: Double): Boolean {
    val x1 = x / r
    val y1 = y / r

    if (x1 <= -0.425 && y1 >= 0 && x1 >= -sqrt (1 - y1*y1/0.25)) {
      return true
    }
    if (x1 <= -0.57 && y1 <= 0 && x1 >= -sqrt (1 - y1*y1/0.25)) {
      return true
    }
    if (x1 >= 0.425 && y1 >= 0 && x1 <= sqrt (1 - y1*y1/0.25)) {
      return true
    }
    if (x1 >= 0.57 && y1 <= 0 && x1 <= sqrt (1 - y1*y1/0.25)) {
      return true
    }
    if (x1 in (-0.57 .. 0.57) && y1 <= 0 &&
            1.0/14.0 * (3.0 * sqrt(1.0 - x1 * x1) + sqrt(1.0 - (abs(abs(7.0*x1) - 2.0) - 1.0)*(abs(abs(7.0*x1) - 2.0) - 1.0)) +
            abs(7.0*x1/2.0) - ((3.0 * sqrt(33.0) - 7.0)/112.0)* 49.0*x1*x1 - 3.0) * (sign(x1 + 4.0) - sign(x1 - 4.0)) - 0.5 * sqrt(1 - x1*x1) <= y1 - 0.015){
      return true
    }
    if (x1 in (0.142 .. 0.425) && y1 >= 0 &&
            y1<= 1.0/7.0 * (6.0/7.0 * sqrt(10.0) + (3.0 + 7.0 * (x1-0.433))/2.0 -
                    3.0/7.0 * sqrt(10.0) * sqrt(4.0 - (7.0 * (x1-0.433) + 1.0)*(7.0 * (x1-0.433) + 1.0)) - 3.0 * sqrt(1.0 - (x1-0.433)*(x1-0.433))) + 0.433){
      return true
    }
    if (x1 in (-0.425 .. -0.142) && y1 >= 0 &&
            y1<= 1.0/7.0 * (6.0/7.0 * sqrt(10.0) + (3.0 + 7.0 * (-x1-0.433))/2.0 -
                    3.0/7.0 * sqrt(10.0) * sqrt(4.0 - (7.0 * (-x1-0.433) + 1.0)*(7.0 * (-x1-0.433) + 1.0)) - 3.0 * sqrt(1.0 - (-x1-0.433)*(-x1-0.433))) + 0.433){
      return true
    }
    if (x1 in (0.105 .. 0.142) && y1 >= 0 &&
            y1 <= 1.0/0.03681 * (0.05437-0.3388*(x1))){
      return true
    }
    if (x1 in (-0.142 .. -0.105) && y1 >= 0 &&
            y1 <= 1.0/0.03681 * (0.05437+0.3388*(x1))){
      return true
    }
    if (x1 in (0.0691 .. 0.105) && y1 >= 0 &&
            y1 <= -(-0.12727*x1 - 0.0049978457)/0.036866){
      return true
    }
    if (x1 in (-0.105 .. -0.0691) && y1 >= 0 &&
            y1 <= -(0.12727*x1 - 0.0049978457)/0.036866){
      return true
    }
    if (x1 in (-0.0691 .. 0.0691) && y1 >= 0 && y1 <= 0.376){
      return true
    }

    return false
  }
}

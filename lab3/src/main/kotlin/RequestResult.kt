import java.io.Serializable

class RequestResult(val x: Double, val y: Double, val r: Double, val isHit: Boolean) : Serializable {
  constructor() : this(0.0, 0.0, 0.0, false)
}

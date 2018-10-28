import java.io.Serializable

class NavigationController : Serializable {

  fun moveToEssential() = "essential"

  companion object {
    private const val serialVersionUID = 1L
  }
}

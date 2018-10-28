import java.io.Serializable

class NavigationController : Serializable {

  fun moveToEssential() = "essential?faces-redirect=true"
  fun moveToIndex() = "index?faces-redirect=true"

  companion object {
    private const val serialVersionUID = 1L
  }
}

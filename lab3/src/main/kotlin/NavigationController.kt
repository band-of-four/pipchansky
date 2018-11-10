import java.io.Serializable

class NavigationController : Serializable {
  fun moveToEssential() = "essential?faces-redirect=true"
  fun moveToIndex() = "index?faces-redirect=true"
}

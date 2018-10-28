import java.io.Serializable

class WelcomeBean : Serializable {
  init {
    println("WelcomeBean instantiated")
  }

  fun getMessage() = "I'm alive!"
}

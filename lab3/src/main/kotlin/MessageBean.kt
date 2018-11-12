import java.io.Serializable

class MessageBean : Serializable {
  var currentMessage = ""

  fun usernameTaken() { currentMessage = "This username is already taken" }
  fun authorizationFailed() { currentMessage = "Wrong login or password" }
  fun allRight() { currentMessage = "" }
  fun dbConnectionProblem() { currentMessage = "There is a connection problem to the database" }
}
import jpa.UserService
import java.io.Serializable

import javax.faces.context.FacesContext
import javax.servlet.http.HttpSession

class HistoryBean(var user: User? = null) : Serializable {

  fun initialize() {
    val userService = UserService()
    val username = (FacesContext.getCurrentInstance().externalContext.getSession(false) as HttpSession)
        .getAttribute("username") as String
    val users = userService.findAllUsers() as MutableList<User>
    this.user = users.find { it.username == username }
  }

  fun getResultsArray(): Array<RequestResult> {
    return user?.history?.toTypedArray() as Array<RequestResult>
  }

  fun addRequestResult(res: RequestResult) {
    if (user == null && user?.history == null) return
    (user?.history as MutableList<RequestResult>).add(res)
    res.user = user
    UserService().updateUser(user!!)
  }

}

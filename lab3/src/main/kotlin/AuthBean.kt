import jpa.UserService
import java.nio.charset.Charset
import java.security.MessageDigest
import javax.faces.context.FacesContext
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession

class AuthBean(var username: String? = null, var password: String? = null) {

  var navigationController: NavigationController? = null
  var messageBean: MessageBean? = null

  fun login(): String? {
    val context = FacesContext.getCurrentInstance().externalContext
    val request = context.request as HttpServletRequest
    return try {
      request.login(username, password)
      (context.getSession(true) as HttpSession).setAttribute("username", username)
      messageBean?.allRight()
      navigationController?.moveToEssential()
    } catch (e: Exception) {
      messageBean?.authorizationFailed()
      return null
    }
  }

  fun signup(): String? {
    val passwordDigest = MessageDigest.getInstance("SHA-256")
    val sha256password = hexEncode(synchronized(passwordDigest) {
      passwordDigest.reset()
      passwordDigest.digest(password?.toByteArray(Charset.forName("UTF-8")))
    })

    val userToInsert = User().apply {
      username = this@AuthBean.username
      password = sha256password
      history = ArrayList<RequestResult>()
    }
    userToInsert.groups = ArrayList<Group>().apply {
      add(Group("user", userToInsert))
    }

    val userService = UserService()
    val users = userService.findAllUsers() // FIXME haha, is there any way better ?
    val registered = users.find { it.username == userToInsert.username }
    if (registered != null) {
      messageBean?.usernameTaken()
      return null
    }
    userService.saveUser(userToInsert)

    val context = FacesContext.getCurrentInstance().externalContext
    val request = context.request as HttpServletRequest
    return try {
      request.login(username, password)
      (context.getSession(true) as HttpSession).setAttribute("username", username)
      messageBean?.allRight()
      navigationController?.moveToEssential()
    } catch (e: Exception) {
      messageBean?.dbConnectionProblem()
      return null
    }
  }

  fun logout(): String? = let {
    FacesContext.getCurrentInstance().externalContext.invalidateSession()
    navigationController?.moveToIndex()
  }

  private fun hexEncode(bytes: ByteArray): String {
    val hexadecimal = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        'a', 'b', 'c', 'd', 'e', 'f')
    val sb = StringBuilder(2 * bytes.size)
    for (byte in bytes) {
      sb.append(hexadecimal[byte.toInt() and 0xf0 shr 4])
      sb.append(hexadecimal[byte.toInt() and 0x0f])
    }
    return sb.toString()
  }
}
import java.nio.charset.Charset
import java.security.MessageDigest
import java.sql.SQLIntegrityConstraintViolationException
import javax.annotation.Resource
import javax.faces.context.FacesContext
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession
import javax.sql.DataSource

class AuthBean(var username: String? = null, var password: String? = null) {

  var navigationController: NavigationController? = null

  @Resource(lookup = "jdbc/OJdbcPool")
  var dataSource: DataSource? = null

  fun login(): String? {
    val context = FacesContext.getCurrentInstance().externalContext
    val request = context.request as HttpServletRequest
    return try {
      request.login(username, password)
      (context.getSession(true) as HttpSession).setAttribute("username", username)
      navigationController?.moveToEssential()
    } catch (e: Exception) {
      throw e
      // TODO show error message
      null
    }
  }

  fun signup(): String? {
    /* Might as well be plaintext... */
    val passwordDigest = MessageDigest.getInstance("SHA-256")
    val sha256password = hexEncode(synchronized(passwordDigest) {
      passwordDigest.reset()
      passwordDigest.digest(password?.toByteArray(Charset.forName("UTF-8")))
    })

    val conn = dataSource?.connection
    val userInsertQuery = conn?.prepareStatement("insert into users values (?, ?)")
    userInsertQuery?.setString(1, username)
    userInsertQuery?.setString(2, sha256password)
    val groupInsertQuery = conn?.prepareStatement("insert into groups values (?, ?)")
    groupInsertQuery?.setString(1, "user")
    groupInsertQuery?.setString(2, username)

    try {
      conn?.autoCommit = false
      userInsertQuery?.executeUpdate()
      groupInsertQuery?.executeUpdate()
      conn?.commit()
      return login()
    } catch (e: SQLIntegrityConstraintViolationException) {
      // TODO username already exists
      conn?.rollback()
    } finally {
      conn?.close()
    }
    return null
  }

  fun logout(): String? = let {
    FacesContext.getCurrentInstance().externalContext.invalidateSession()
    navigationController?.moveToEssential()
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

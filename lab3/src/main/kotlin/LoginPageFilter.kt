import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.ServletException
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletResponse
import javax.servlet.ServletRequest
import javax.servlet.FilterConfig
import javax.servlet.Filter

class LoginPageFilter : Filter {

  @Throws(ServletException::class)
  override fun init(filterConfig: FilterConfig) { }

  @Throws(IOException::class, ServletException::class)
  override fun doFilter(servletRequest: ServletRequest, servletResponse: ServletResponse, filterChain: FilterChain) {
    val request = servletRequest as HttpServletRequest
    val response = servletResponse as HttpServletResponse

    if (request.userPrincipal != null) { // If user is already authenticated
      response.sendRedirect(request.contextPath + "/essential.xhtml")
    } else {
      filterChain.doFilter(servletRequest, servletResponse)
    }
  }

  override fun destroy() { }
}
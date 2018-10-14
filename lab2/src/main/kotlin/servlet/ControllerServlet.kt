package servlet

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet("/")
class ControllerServlet : HttpServlet() {
  override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
    val staticTypes = arrayOf("png", "css", "jpg", "ico", "js")

    if (req.requestURI.split(".").last() in staticTypes) {
      resp.setHeader("Content-Type", servletContext.getMimeType(req.requestURI))
      try {
        servletContext.getResourceAsStream(req.requestURI).copyTo(resp.outputStream)
      }
      catch (e: Exception) {
        e.printStackTrace()
        resp.sendError(404)
      }
    } else {
      resp.contentType = "text/html;charset=UTF-8"
      req.getRequestDispatcher("index.jsp").forward(req, resp)
    }
  }

  override fun doPost(req: HttpServletRequest, resp: HttpServletResponse) {
    if (req.requestURI.trimEnd { it == '/' } == "/AreaCheckServlet") {
      req.servletContext.getNamedDispatcher("AreaCheckServlet").forward(req, resp)
    } else { resp.sendError(404) }
  }
}

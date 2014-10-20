package webapp.computer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 * Servlet implementation class SpringHttpServlet
 */
public class SpringHttpServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  public void init(final ServletConfig config) throws ServletException {
    super.init(config);
    SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
        config.getServletContext());
  }
}

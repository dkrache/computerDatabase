package webapp.computer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webapp.utils.Constants;

/**
 * Servlet implementation class ComputerCrudServlet
 */
@WebServlet(Constants.SERVLET_ACCUEIL)
public class AccueilServlet extends SpringHttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * Default constructor
   */
  public AccueilServlet() {
    super();
  }

  /* (non-Javadoc)
   * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
   */
  @Override
  protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
      throws ServletException, IOException {

    request.getServletContext().getRequestDispatcher(Constants.SERVLET_SHOW_ALL_COMPUTER)
        .forward(request, response);

  }

}

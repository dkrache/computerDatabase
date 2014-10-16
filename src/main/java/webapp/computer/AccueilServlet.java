package webapp.computer;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webapp.utils.Constants;

/**
 * Servlet implementation class ComputerCrudServlet
 */
@WebServlet(Constants.SERVLET_ACCUEIL)
public class AccueilServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * Default constructor
   * @see HttpServlet#HttpServlet()
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
    doPost(request, response);
  }

  /* (non-Javadoc)
   * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
   */
  @Override
  protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
      throws ServletException, IOException {
    final RequestDispatcher requestDispatcher = request.getServletContext().getRequestDispatcher(
        "/ShowAllComputers");

    requestDispatcher.forward(request, response);

  }
}

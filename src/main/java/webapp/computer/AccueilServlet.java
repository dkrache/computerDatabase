package webapp.computer;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.IComputerService;
import service.impl.ComputerService;

/**
 * Servlet implementation class ComputerCrudServlet
 */
@WebServlet("/Accueil")
public class AccueilServlet extends HttpServlet {
  private static final long             serialVersionUID = 1L;
  private static final IComputerService COMPUTER_SERVICE = new ComputerService();

  /**
   * @see HttpServlet#HttpServlet()
   */
  public AccueilServlet() {
    super();
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletRes!StringUtils.isNullOrEmpty(request.getParameter("company"))ponse response)
   */
  protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
      throws ServletException, IOException {
    doPost(request, response);
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
      throws ServletException, IOException {
    final RequestDispatcher requestDispatcher = request.getServletContext().getRequestDispatcher(
        "/ShowAllComputers");

    requestDispatcher.forward(request, response);

  }
}

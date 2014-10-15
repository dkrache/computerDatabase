package webapp.computer;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import service.IComputerService;
import service.exception.ServiceException;
import service.impl.ComputerService;

/**
 * Servlet implementation class ComputerCrudServlet
 */
@WebServlet("/DeleteComputer")
public class DeleteComputerServlet extends HttpServlet {
  private static final long             serialVersionUID  = 1L;
  private static final IComputerService COMPUTER_SERVICE  = new ComputerService();
  public static final String            PARAM_ID_COMPUTER = "codereq";
  private static final String           PARAM_MESSAGE     = "message";

  /**
   * @see HttpServlet#HttpServlet()
   */
  public DeleteComputerServlet() {
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
        "/Accueil");
    try {

      if (request.getParameter(PARAM_ID_COMPUTER) != null
          && StringUtils.isNumeric(request.getParameter(PARAM_ID_COMPUTER))) {
        COMPUTER_SERVICE.delete(Integer.parseInt((String) request.getParameter(PARAM_ID_COMPUTER)));
        request.setAttribute(PARAM_MESSAGE, "Ordinateur supprimé avec succès");
      }
    } catch (final NumberFormatException | ServiceException e) {
      e.printStackTrace();
    }
    requestDispatcher.forward(request, response);

  }
}

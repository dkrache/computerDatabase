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
import webapp.dto.ComputerDto;

/**
 * Servlet implementation class ComputerCrudServlet
 */
@WebServlet("/UpdateComputer")
public class UpdateComputerServlet extends HttpServlet {
  private static final long             serialVersionUID  = 1L;
  private static final IComputerService COMPUTER_SERVICE  = new ComputerService();
  public static final String            PARAM_ID_COMPUTER = "codereq";

  /**
   * @see HttpServlet#HttpServlet()
   */
  public UpdateComputerServlet() {
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
    RequestDispatcher requestDispatcher = request.getServletContext().getRequestDispatcher(
        "/Accueil");
    try {
      if (request.getParameter(PARAM_ID_COMPUTER) != null
          && StringUtils.isNumeric(request.getParameter(PARAM_ID_COMPUTER))) {
        final ComputerDto computerDto = COMPUTER_SERVICE.select(Integer.parseInt((String) request
            .getParameter(PARAM_ID_COMPUTER)));
        if (computerDto != null) {
          request.setAttribute("computer", computerDto);
          requestDispatcher = request.getServletContext().getRequestDispatcher(
              "/jsp/addComputer.jsp");
        }
      }
    } catch (final NumberFormatException | ServiceException e) {
      e.printStackTrace();
    }
    requestDispatcher.forward(request, response);
  }
}

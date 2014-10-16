package webapp.computer;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import service.IComputerService;
import service.exception.ServiceException;
import service.impl.ComputerService;
import webapp.dto.ComputerDto;
import binding.ComputerMapper;

/**
 * Servlet implementation class ComputerCrudServlet
 */
@WebServlet("/ShowAllComputers")
public class ShowAllComputersServlet extends HttpServlet {
  private static final String           PARAM_ERROR      = "error";
  private static final String           PARAM_MESSAGE    = "message";
  private static final long             serialVersionUID = 1L;
  private static final IComputerService COMPUTER_SERVICE = new ComputerService();
  private static final Logger           LOGGER           = LoggerFactory
                                                             .getLogger(ShowAllComputersServlet.class);

  /**
   * @see HttpServlet#HttpServlet()
   */
  public ShowAllComputersServlet() {
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
        "/jsp/dashboard.jsp");
    int offset = 0;
    try {
      offset = Integer.parseInt(request.getParameter("offset"));
    } catch (final NumberFormatException e) {
      LOGGER.warn("Error, offset incorrect", e);
    }
    try {
      final List<ComputerDto> computerDtos;
      if (request.getParameter("search") != null) {
        computerDtos = ComputerMapper.toDto(COMPUTER_SERVICE.search(
            (String) request.getParameter("search"), offset));
      } else {
        computerDtos = ComputerMapper.toDto(COMPUTER_SERVICE.selectAll(offset));
      }
      if (computerDtos == null || computerDtos.size() == 0) {
        request.setAttribute(PARAM_ERROR, true);
        request.setAttribute(PARAM_MESSAGE, "Any element was found.");
      }
      request.setAttribute("computers", computerDtos);
    } catch (final ServiceException e) {
      request.setAttribute(PARAM_ERROR, true);
      request.setAttribute(PARAM_MESSAGE, e.getMessage());
    }
    requestDispatcher.forward(request, response);

  }
}

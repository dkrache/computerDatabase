package webapp.computer;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import service.IComputerService;
import service.exception.ServiceException;
import service.impl.ComputerService;
import webapp.dto.ComputerDto;
import webapp.utils.Constants;
import binding.ComputerMapper;

/**
 * Servlet implementation class ComputerCrudServlet
 */
@WebServlet(Constants.SERVLET_UPDATE_COMPUTER)
public class UpdateComputerServlet extends HttpServlet {
  private static final long             serialVersionUID  = 1L;
  private static final IComputerService COMPUTER_SERVICE  = new ComputerService();
  private static final String           PARAM_ID_COMPUTER = "codereq";
  private static final Logger           LOGGER            = LoggerFactory
                                                              .getLogger(UpdateComputerServlet.class);

  /**
   * Default constructor.
   */
  public UpdateComputerServlet() {
    super();
  }

  /* (non-Javadoc)
   * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
   */
  @Override
  protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
      throws ServletException, IOException {
    RequestDispatcher requestDispatcher = request.getServletContext().getRequestDispatcher(
        Constants.JSP_ADD_COMPUTER);
    requestDispatcher.forward(request, response);
  }

  /* (non-Javadoc)
   * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
   */
  @Override
  protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
      throws ServletException, IOException {
    RequestDispatcher requestDispatcher = request.getServletContext().getRequestDispatcher(
        Constants.SERVLET_ACCUEIL);
    try {
      if (request.getParameter(PARAM_ID_COMPUTER) != null
          && StringUtils.isNumeric(request.getParameter(PARAM_ID_COMPUTER))) {
        final ComputerDto computerDto = ComputerMapper.toDto(COMPUTER_SERVICE.select(Integer
            .parseInt((String) request.getParameter(PARAM_ID_COMPUTER))));
        if (computerDto != null) {
          request.setAttribute("computer", computerDto);
          request.setAttribute("message", "You will modify the computer whose the name is : "
              + computerDto.getComputerName());
          requestDispatcher = request.getServletContext().getRequestDispatcher(
              Constants.JSP_ADD_COMPUTER);
        }
      }
    } catch (final NumberFormatException | ServiceException e) {
      LOGGER.warn("Error : Impossible to answer to the update request : {}", e);
    }
    requestDispatcher.forward(request, response);
  }
}

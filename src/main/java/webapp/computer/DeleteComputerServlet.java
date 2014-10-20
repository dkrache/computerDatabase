package webapp.computer;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import service.IComputerService;
import service.exception.ServiceException;
import webapp.utils.Constants;

/**
 * Servlet implementation class ComputerCrudServlet
 */
@WebServlet(Constants.SERVLET_DELETE_COMPUTER)
public class DeleteComputerServlet extends SpringHttpServlet {
  private static final long   serialVersionUID  = 1L;
  @Autowired
  private IComputerService    computerService;
  private static final String PARAM_ID_COMPUTER = "codereq";
  private static final String PARAM_MESSAGE     = "message";
  private static final Logger LOGGER            = LoggerFactory
                                                    .getLogger(DeleteComputerServlet.class);

  /**
   * Default constructor
   */
  public DeleteComputerServlet() {
    super();
  }


  /* (non-Javadoc)
   * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
   */
  @Override
  protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
      throws ServletException, IOException {
    final RequestDispatcher requestDispatcher = request.getServletContext().getRequestDispatcher(
        Constants.SERVLET_ACCUEIL);
    try {

      if (request.getParameter(PARAM_ID_COMPUTER) != null
          && StringUtils.isNumeric(request.getParameter(PARAM_ID_COMPUTER))) {
        computerService.delete(Integer.parseInt((String) request.getParameter(PARAM_ID_COMPUTER)));
        request.setAttribute(PARAM_MESSAGE, "Computer deleted");
      }
    } catch (final NumberFormatException | ServiceException e) {
      LOGGER.warn("Error: impossible to delete the object Computer", e);
    }
    requestDispatcher.forward(request, response);
  }

  /* (non-Javadoc)
   * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
   */
  @Override
  protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
      throws ServletException, IOException {
    request.getServletContext().getRequestDispatcher(Constants.SERVLET_ACCUEIL)
        .forward(request, response);
  }

  /**
   * @param computerService the computerService to set
   */
  public void setComputerService(final IComputerService computerService) {
    this.computerService = computerService;
  }
}

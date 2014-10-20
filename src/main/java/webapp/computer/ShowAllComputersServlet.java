package webapp.computer;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import service.IComputerService;
import service.exception.ServiceException;
import webapp.dto.ComputerDto;
import webapp.utils.Constants;
import webapp.utils.PageUtils;
import binding.ComputerMapper;
import core.Page;

/**
 * Servlet implementation class ComputerCrudServlet
 */
@WebServlet(Constants.SERVLET_SHOW_ALL_COMPUTER)
public class ShowAllComputersServlet extends SpringHttpServlet {
  private static final String        PARAM_ERROR      = "error";
  private static final String        PARAM_MESSAGE    = "message";
  private static final long          serialVersionUID = 1L;
  @Autowired
  private transient IComputerService computerService;
  private static final Logger        LOGGER           = LoggerFactory
                                                          .getLogger(ShowAllComputersServlet.class);

  /* (non-Javadoc)
   * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
   */
  @Override
  protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
      throws ServletException, IOException {
    final RequestDispatcher requestDispatcher = request.getServletContext().getRequestDispatcher(
        Constants.JSP_DASHBOARD);
    try {
      final Page page = PageUtils.createPage(request);
      final List<ComputerDto> computerDtos = ComputerMapper.toDto(computerService.search(page));
      if (computerDtos == null || computerDtos.isEmpty()) {
        request.setAttribute(PARAM_ERROR, true);
        request.setAttribute(PARAM_MESSAGE, "Any element was found.");
      }
      request.setAttribute("computers", computerDtos);
      request.setAttribute("page", page);
    } catch (final ServiceException e) {
      LOGGER.warn("Error : Impossible to print computers : {}", e);
      request.setAttribute(PARAM_ERROR, true);
      request.setAttribute(PARAM_MESSAGE, e.getMessage());
    }
    requestDispatcher.forward(request, response);
  }

  /**
   * @param computerService the computerService to set
   */
  public IComputerService getComputerService() {
    return computerService;
  }

  /**
   * @param computerService the computerService to set
   */
  public void setComputerService(final IComputerService computerService) {
    this.computerService = computerService;
  }

}

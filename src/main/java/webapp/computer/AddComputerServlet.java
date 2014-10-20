package webapp.computer;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import service.IComputerService;
import service.exception.ServiceException;
import webapp.dto.CompanyDto;
import webapp.dto.ComputerDto;
import webapp.utils.Constants;
import binding.ComputerMapper;

/**
 * Servlet implementation class ComputerCrudServlet
 */
/**
 * @author excilys
 *
 */
@WebServlet(Constants.SERVLET_ADD_COMPUTER)
public class AddComputerServlet extends SpringHttpServlet {
  private static final String        PARAM_COMPANY     = "company";
  private static final String        PARAM_MESSAGE     = "message";
  private static final long          serialVersionUID  = 1L;
  private static final Logger        LOGGER            = LoggerFactory
                                                           .getLogger(AddComputerServlet.class);
  @Autowired
  private transient IComputerService computerService;
  public static final String         PARAM_ID_COMPUTER = "externalId";

  /**
   * Default constructor
   */
  public AddComputerServlet() {
    super();
  }

  /* (non-Javadoc)
   * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
   */
  @Override
  protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
      throws ServletException, IOException {
    request.getServletContext().getRequestDispatcher(Constants.JSP_ADD_COMPUTER)
        .forward(request, response);
  }

  /* (non-Javadoc)
   * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
   */
  @Override
  protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
      throws ServletException, IOException {

    final ComputerDto computerDto = new ComputerDto();
    computerDto.setComputerName(request.getParameter("name"));
    computerDto.setIntroducedDate(request.getParameter("introducedDate"));
    computerDto.setDiscontinuedDate(request.getParameter("discontinuedDate"));
    if (!StringUtils.isEmpty(request.getParameter(PARAM_COMPANY))
        && StringUtils.isNumeric(request.getParameter(PARAM_COMPANY))) {
      final CompanyDto companyDto = new CompanyDto();
      companyDto.setExternalId(Long.parseLong(request.getParameter(PARAM_COMPANY)));
      computerDto.setCompanyDto(companyDto);
    }
    try {
      if (request.getParameter(PARAM_ID_COMPUTER) != null
          && StringUtils.isNumeric(request.getParameter(PARAM_ID_COMPUTER))) {
        computerDto.setExternalId(Long.parseLong(request.getParameter(PARAM_ID_COMPUTER)));
        computerService.update(ComputerMapper.fromDto(computerDto));
        request.setAttribute(PARAM_MESSAGE, "Computer updated");
      } else {
        if (computerService.insert(ComputerMapper.fromDto(computerDto))) {
          request.setAttribute(PARAM_MESSAGE, "Computer Added");
        }
      }

    } catch (final ParseException | ServiceException e) {
      LOGGER.warn("Error : Impossible to add computer : {}", e);
      request.setAttribute("error", true);
      request.setAttribute(PARAM_MESSAGE, e.getMessage());

    }
    response.sendRedirect(response.encodeRedirectURL("/computer-database/Accueil"));

  }

  /**
   * @param computerService the computerService to set
   */
  public void setComputerService(final IComputerService computerService) {
    this.computerService = computerService;
  }
}

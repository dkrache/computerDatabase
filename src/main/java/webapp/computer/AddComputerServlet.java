package webapp.computer;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import service.ICompanyService;
import service.IComputerService;
import service.exception.ServiceException;
import service.impl.CompanyService;
import service.impl.ComputerService;
import webapp.dto.CompanyDto;
import webapp.dto.ComputerDto;

/**
 * Servlet implementation class ComputerCrudServlet
 */
/**
 * @author excilys
 *
 */
@WebServlet("/AddComputer")
public class AddComputerServlet extends HttpServlet {
  private static final String           PARAM_COMPANY     = "company";
  private static final String           PARAM_MESSAGE     = "message";
  private static final long             serialVersionUID  = 1L;
  private static final IComputerService COMPUTER_SERVICE  = new ComputerService();

  public static final String            PARAM_ID_COMPUTER = "externalId";

  /**
   * @see HttpServlet#HttpServlet()
   */
  public AddComputerServlet() {
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
        "/jsp/addComputer.jsp");

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
        COMPUTER_SERVICE.update(computerDto);
        requestDispatcher = request.getServletContext().getRequestDispatcher("/Accueil");
        request.setAttribute(PARAM_MESSAGE, "Ordinateur mis à jour avec succès");
      } else {
        if (COMPUTER_SERVICE.insert(computerDto)) {
          request.setAttribute(PARAM_MESSAGE, "Ordinateur ajouté avec succès");
        }
      }

    } catch (final ServiceException e) {
      request.setAttribute("error", true);
      request.setAttribute(PARAM_MESSAGE, e.getMessage());

    }
    requestDispatcher.forward(request, response);

  }
}

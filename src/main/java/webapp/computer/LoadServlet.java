package webapp.computer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import service.ICompanyService;
import service.exception.ServiceException;
import service.impl.CompanyService;
import binding.CompanyMapper;

/**
 * @author excilys
 *
 */
public final class LoadServlet extends HttpServlet {
  private static final long            serialVersionUID = 1L;
  private static final ICompanyService COMPANY_SERVICE  = new CompanyService();
  private static final Logger          LOGGER           = LoggerFactory
                                                            .getLogger(LoadServlet.class);

  /**
   * Default Constructor 
   * @see HttpServ"/DeleteComputer"let#HttpServlet()
   */
  public LoadServlet() {
    super();

  }

  /* (non-Javadoc)
   * @see javax.servlet.GenericServlet#init()
   */
  @Override
  public void init() {
    try {
      this.getServletContext().setAttribute("companys",
          CompanyMapper.toDto(COMPANY_SERVICE.selectAll()));
    } catch (final ServiceException e) {
      LOGGER.error("Error while getting companys", e);
    }
  }

  /* (non-Javadoc)
   * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
   */
  @Override
  protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
      throws ServletException, IOException {

  }

  /* (non-Javadoc)
   * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
   */
  @Override
  protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }

}

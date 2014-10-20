package webapp.computer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import service.ICompanyService;
import service.exception.ServiceException;
import service.impl.CompanyService;
import binding.CompanyMapper;

/**
 * @author excilys
 *
 */
@Component
public final class LoadServlet extends SpringHttpServlet {
  private static final long         serialVersionUID = 1L;
  @Autowired
  private transient ICompanyService companyService   = new CompanyService();
  private static final Logger       LOGGER           = LoggerFactory.getLogger(LoadServlet.class);

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
          CompanyMapper.toDto(companyService.selectAll()));
    } catch (final ServiceException e) {
      LOGGER.error("Error while getting companys", e);
    }
  }

  /**
   * @param companyService the companyService to set
   */
  public void setCompanyService(final ICompanyService companyService) {
    this.companyService = companyService;
  }

}

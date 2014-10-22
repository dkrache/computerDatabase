package webapp.computer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import service.ICompanyService;
import service.exception.ServiceException;
import service.impl.CompanyService;
import binding.CompanyMapper;

/**
 * @author excilys
 *
 */
public final class LoadServlet extends HttpServlet {
  private static final long   serialVersionUID = 1L;
  private ICompanyService     companyService   = new CompanyService();
  private static final Logger LOGGER           = LoggerFactory.getLogger(LoadServlet.class);

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

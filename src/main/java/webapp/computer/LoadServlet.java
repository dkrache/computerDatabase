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

public final class LoadServlet extends HttpServlet {
  private static final long            serialVersionUID = 1L;
  private static final ICompanyService COMPANY_SERVICE  = new CompanyService();
  private static final Logger          LOGGER           = LoggerFactory
                                                            .getLogger(LoadServlet.class);

  /**
   * @see HttpServlet#HttpServlet()
   */
  public LoadServlet() {
    super();

  }

  @Override
  public void init() {
    try {
      this.getServletContext().setAttribute("companys", COMPANY_SERVICE.selectAll());
    } catch (final ServiceException e) {
      LOGGER.error("erreur lors de l'extraction des données", e);
    }
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
      throws ServletException, IOException {

  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }

}

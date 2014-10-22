package webapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import service.IComputerService;
import service.exception.ServiceException;
import webapp.utils.Constants;

/**
 * Servlet implementation class ComputerCrudServlet
 */
@Controller
@RequestMapping(Constants.VUE_DELETE_COMPUTER)
public class DeleteComputerController {
  @Autowired
  private IComputerService    computerService;
  private static final String PARAM_MESSAGE = "message";
  private static final Logger LOGGER        = LoggerFactory
                                                .getLogger(DeleteComputerController.class);

  /* (non-Javadoc)
   * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
   */
  @RequestMapping(method = RequestMethod.GET)
  protected String doGet(@RequestParam
  final int codereq, final ModelMap model) {
    try {
      computerService.delete(codereq);
      model.addAttribute(PARAM_MESSAGE, "Computer deleted");
    } catch (final ServiceException e) {
      LOGGER.warn("Error: impossible to delete the object Computer", e);
    }
    return Constants.REDIRECT + Constants.VUE_DASHBOARD;
  }

  /**
   * @param computerService the computerService to set
   */
  public void setComputerService(final IComputerService computerService) {
    this.computerService = computerService;
  }
}

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
import webapp.dto.ComputerDto;
import webapp.utils.Constants;
import binding.ComputerMapper;

/**
 * Servlet implementation class ComputerCrudServlet
 */
@Controller
@RequestMapping(Constants.VUE_UPDATE_COMPUTER)
public class UpdateComputerController {
  @Autowired
  private transient IComputerService computerService;
  private static final Logger        LOGGER = LoggerFactory
                                                .getLogger(UpdateComputerController.class);

  /* (non-Javadoc)
   * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
   */
  @RequestMapping(method = RequestMethod.GET)
  protected String doGet(@RequestParam(required = false, defaultValue = "-1")
  final int codereq, final ModelMap model) {
    try {
      final ComputerDto computerDto = ComputerMapper.toDto(computerService.select(codereq));
      if (computerDto != null) {
        model.addAttribute("computerDto", computerDto);
        model.addAttribute(Constants.PARAM_MESSAGE, "You will update this computer");
      } else {
        model.addAttribute(Constants.PARAM_ERROR, "The computer wasn't found.");
        return Constants.REDIRECT + Constants.VUE_DASHBOARD;
      }

    } catch (final NumberFormatException | ServiceException e) {
      LOGGER.warn("Error : Impossible to answer to the update request : {}", e);
    }
    return Constants.VUE_ADD_COMPUTER;
  }

  /**
   * @param computerService the computerService to set
   */
  public void setComputerService(final IComputerService computerService) {
    this.computerService = computerService;
  }
}

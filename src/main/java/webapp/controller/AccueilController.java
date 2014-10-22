package webapp.controller;

import java.util.List;

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
import core.Page;

/**
 * Servlet implementation class ComputerCrudServlet
 */
@Controller
@RequestMapping(Constants.VUE_DASHBOARD)
public class AccueilController {

  @Autowired
  private IComputerService    computerService;
  private static final Logger LOGGER = LoggerFactory.getLogger(AccueilController.class);

  @RequestMapping(method = RequestMethod.GET)
  //currentPage=0&&searchString=&ascendancy=down&order=ddate
  protected String doGet(@RequestParam(required = false, defaultValue = "10")
  final int limit, @RequestParam(required = false, defaultValue = "")
  final String searchString, @RequestParam(required = false, defaultValue = "0")
  final int currentPage, @RequestParam(required = false, defaultValue = "asc")
  final String ascendancy, @RequestParam(required = false, defaultValue = "name")
  final String order, final ModelMap model) {

    try {
      final Page page = Page.builder().limit(limit).searchString(searchString).order(order)
          .currentPage(currentPage).ascendancy(ascendancy).build();
      final List<ComputerDto> computerDtos = ComputerMapper.toDto(computerService.search(page));
      model.addAttribute("computers", computerDtos);
      model.addAttribute("page", page);
    } catch (final ServiceException e) {
      LOGGER.warn("Error : Impossible to print computers : {}", e);
      model.addAttribute(Constants.PARAM_ERROR, true);
      model.addAttribute(Constants.PARAM_MESSAGE, e.getMessage());
    }
    return Constants.VUE_DASHBOARD;

  }

  /**
   * @param computerService the computerService to set
   */
  public void setComputerService(final IComputerService computerService) {
    this.computerService = computerService;
  }

}

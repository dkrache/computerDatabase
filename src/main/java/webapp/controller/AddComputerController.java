package webapp.controller;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.IComputerService;
import service.exception.ServiceException;
import webapp.dto.CompanyDto;
import webapp.dto.ComputerDto;
import webapp.utils.Constants;
import binding.ComputerMapper;

/**
 * Servlet implementation class ComputerCrudServlet
 */
@Controller
@RequestMapping(Constants.VUE_ADD_COMPUTER)
public class AddComputerController {
  @Autowired
  private IComputerService    computerService;
  private static final Logger LOGGER = LoggerFactory.getLogger(AddComputerController.class);

  @RequestMapping(method = RequestMethod.GET)
  protected String doGet(final ModelMap model) {

    model.addAttribute("computerDto", new ComputerDto());
    return Constants.VUE_ADD_COMPUTER;

  }

  @RequestMapping(method = RequestMethod.POST)
  protected String doPost(@Valid
  final ComputerDto computerDto, final BindingResult result) {
    if (result.hasErrors()) {
      return Constants.VUE_ADD_COMPUTER;
    }
    try {
      if (computerDto != null && computerDto.getCompanyDto() != null
          && computerDto.getCompanyDto().getExternalId() > 0) {
        computerService.update(ComputerMapper.fromDto(computerDto));
      } else {
        computerService.insert(ComputerMapper.fromDto(computerDto));
      }

    } catch (final ParseException | ServiceException e) {
      LOGGER.warn("Error : Impossible to add computer : {}", e);
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

package webapp.controller;

import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
@RequestMapping({ Constants.VUE_COMPUTER, "/accueil", "/dashboard" })
public class ComputerController {
  @Autowired
  private IComputerService    computerService;

  private static final Logger LOGGER = LoggerFactory.getLogger(ComputerController.class);

  /**
   * @param limit
   * @param searchString
   * @param currentPage
   * @param ascendancy
   * @param order
   * @param model
   * @return
   */
  @RequestMapping(method = RequestMethod.GET)
  protected String accueil(@RequestParam(required = false, defaultValue = "10")
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

  @RequestMapping(value = Constants.VUE_ADD, method = RequestMethod.GET)
  protected String doGet(final ModelMap model) {

    model.addAttribute("computerDto", new ComputerDto());
    return Constants.VUE_COMPUTER;

  }

  @RequestMapping(value = Constants.VUE_ADD, method = RequestMethod.POST)
  protected String addComputer(@Valid
  final ComputerDto computerDto, final BindingResult result, final RedirectAttributes redirectAttrs) {
    if (result.hasErrors()) {
      return Constants.VUE_COMPUTER;
    }
    try {
      if (computerDto != null && computerDto.getExternalId() > 0) {
        computerService.update(ComputerMapper.fromDto(computerDto));
        redirectAttrs.addFlashAttribute(Constants.PARAM_MESSAGE, "back.message.computer.updated");
      } else {
        computerService.create(ComputerMapper.fromDto(computerDto));
        redirectAttrs.addFlashAttribute(Constants.PARAM_MESSAGE, "back.message.computer.added");
      }

    } catch (final ParseException | ServiceException e) {
      LOGGER.warn("Error : Impossible to add computer : {}", e);
    }
    return Constants.REDIRECT + '/' + Constants.VUE_COMPUTER;

  }

  /**
   * @param codereq
   * @param redirectAttrs
   * @return
   */
  @RequestMapping(value = Constants.VUE_DELETE, method = RequestMethod.GET)
  protected String delete(@RequestParam
  final int codereq, final RedirectAttributes redirectAttrs) {
    try {
      computerService.delete(codereq);
      redirectAttrs.addFlashAttribute(Constants.PARAM_MESSAGE, "back.message.computer.deleted");
    } catch (final ServiceException e) {
      LOGGER.warn("Error: impossible to delete the object Computer", e);
    }
    return Constants.REDIRECT + '/' + Constants.VUE_COMPUTER;
  }

  /* (non-Javadoc)
   * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
   */
  @RequestMapping(value = Constants.VUE_UPDATE, method = RequestMethod.GET)
  protected String update(@RequestParam(required = false, defaultValue = "-1")
  final int codereq, final ModelMap model, final RedirectAttributes redirectAttrs) {
    try {
      final ComputerDto computerDto = ComputerMapper.toDto(computerService.read(codereq));
      if (computerDto != null) {
        model.addAttribute("computerDto", computerDto);
        model.addAttribute(Constants.PARAM_MESSAGE, "back.message.computer.updating");
      } else {
        redirectAttrs.addFlashAttribute(Constants.PARAM_ERROR, true);
        redirectAttrs.addFlashAttribute(Constants.PARAM_MESSAGE,
            "back.message.error.computer.notFound");
        return Constants.REDIRECT + Constants.VUE_DASHBOARD;
      }
    } catch (final NumberFormatException | ServiceException e) {
      LOGGER.warn("Error : Impossible to answer to the update request : {}", e);
    }
    return Constants.VUE_COMPUTER;
  }

}

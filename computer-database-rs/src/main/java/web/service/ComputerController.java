package web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import service.IComputerService;
import core.Computer;
import core.Page;

/**
 * @author excilys
 * Mon premier Web Service à Excilys
 */

@RestController
@RequestMapping("/computer")
public class ComputerController {
  @Autowired
  private IComputerService    computerService;
  private static final Logger LOGGER = LoggerFactory.getLogger(ComputerController.class);

  @RequestMapping(value = "/search", method = RequestMethod.POST)
  public Page getComputers(@RequestBody
  final Page page) {
    LOGGER.info("return computers ");
    page.setComputers(computerService.search(page));
    return page;
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public Computer getComputerById(@PathVariable
  final int id) {
    LOGGER.info("return computer where id=" + id);
    return computerService.read(id);
  }

  @RequestMapping(method = RequestMethod.PUT)
  public String createComputer(final Computer computer) {
    LOGGER.info("create computer");
    computerService.create(computer);
    return "create";
  }

  @RequestMapping(method = RequestMethod.POST)
  public String updateComputer(final Computer computer) {
    LOGGER.info("update computer");
    computerService.update(computer);
    return "update";
  }

  @RequestMapping(method = RequestMethod.DELETE)
  public String deleteComputer(final int id) {
    LOGGER.info("delete computer");
    computerService.delete(id);
    return "deleted";
  }

}

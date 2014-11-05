package web.service;

import java.io.IOException;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import service.IComputerService;
import core.Computer;
import core.Page;

/**
 * @author excilys
 * Mon premier Web Service à Excilys
 */
@Component
@WebService(endpointInterface = "web.service.IComputerWebService")
public class ComputerWebService implements IComputerWebService {
  @Autowired
  private IComputerService    computerService = (IComputerService) new ClassPathXmlApplicationContext(
                                                  "/serviceApplicationContext.xml").getBean(
                                                  "computerService", IComputerService.class);
  private static final Logger LOGGER          = LoggerFactory.getLogger(ComputerWebService.class);

  /**
   * @param number
   * @param name
   * @return
   * @throws IOException 
   * @throws JsonMappingException 
   * @throws JsonGenerationException 
   */
  @Override
  public String searchComputerAsString(@WebParam(name = "limit")
  final int number, @WebParam(name = "name")
  final String name) {
    List<Computer> computers = computerService.search(Page.builder().limit(number)
        .searchString(name).build());
    ObjectMapper mapper = new ObjectMapper();

    try {
      return mapper.writeValueAsString(computers);
    } catch (IOException e) {
      LOGGER.warn("Error lors de la conversion en json de l'objet :  {} ", e);
    }
    return name;
  }

  /**
   * @param number
   * @param name
   * @return
   * @throws IOException 
   * @throws JsonMappingException 
   * @throws JsonGenerationException 
   */
  @Override
  public Page searchComputers(@WebParam(name = "page") Page page) {
    page.setComputers(computerService.search(page));
    return page;

  }
}

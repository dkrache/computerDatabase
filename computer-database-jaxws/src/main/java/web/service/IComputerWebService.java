package web.service;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import core.Page;

/**
 * @author excilys
 * Mon premier Web Service à Excilys
 */

@WebService
@SOAPBinding(style = Style.DOCUMENT)
public interface IComputerWebService {

  /**
   * @param limit
   * @param name
   * @return
   */
  String searchComputerAsString(@WebParam(name = "limit") int limit,
      @WebParam(name = "name") String name);

  /**
   * @param limit
   * @param name
   * @return
   */
  Page searchComputers(@WebParam(name = "page") Page page);
}

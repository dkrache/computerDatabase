package service;

import java.util.List;

import core.Company;

/**
 * @author excilys
 *
 */
public interface ICompanyService {

  /**
   * Get all the Companys
   * @return
   */
  List<Company> readAll();

}

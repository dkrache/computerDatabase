package persistence;

import java.util.List;

import core.Company;

/**
 * @author excilys
 * 
 */
public interface ICompanyDAO extends MyBaseRepository<Company, Long> {

  /**
   * Extract All company
   * @return
   */
  List<Company> findAll();

}
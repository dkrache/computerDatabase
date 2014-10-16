package service;

import java.util.List;

import service.exception.ServiceException;
import core.Company;

/**
 * @author excilys
 *
 */
public interface ICompanyService {
  /**
   * Get the list of all companys
   * @return .
   * @throws ServiceException
   */
  List<Company> selectAll();
  //
  //  CompanyDto select(final long externalIdCompany) throws ServiceException;
  //
  //  boolean insert(final CompanyDto CompanyDto) throws ServiceException;
  //
  //  boolean update(final CompanyDto CompanyDto) throws ServiceException;
  //
  //  void delete(final long externalIdCompany) throws ServiceException;
}

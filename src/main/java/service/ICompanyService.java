package service;

import java.util.List;

import service.exception.ServiceException;
import webapp.dto.CompanyDto;

/**
 * @author excilys
 *
 */
public interface ICompanyService {
  /**
   * @return la totalité des companys présente en base de données.
   * @throws ServiceException
   */
  List<CompanyDto> selectAll() throws ServiceException;
  //
  //  CompanyDto select(final long externalIdCompany) throws ServiceException;
  //
  //  boolean insert(final CompanyDto CompanyDto) throws ServiceException;
  //
  //  boolean update(final CompanyDto CompanyDto) throws ServiceException;
  //
  //  void delete(final long externalIdCompany) throws ServiceException;
}

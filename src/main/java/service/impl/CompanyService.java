package service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import persistence.ICompanyDAO;
import persistence.IConnectionDAO;
import persistence.exception.PersistenceException;
import service.ICompanyService;
import service.exception.ServiceException;
import core.Company;

/**
 * @author excilys
 *
 */
@Service
public class CompanyService implements ICompanyService {
  private static final Logger LOGGER = LoggerFactory.getLogger(CompanyService.class);
  @Autowired
  private IConnectionDAO      connectionDAO;
  @Autowired
  private ICompanyDAO         companyDAO;

  /* (non-Javadoc)
   * @see service.ICompanyService#selectAll()
   */
  @Override
  public List<Company> selectAll() {
    try {
      return companyDAO.selectAll();
    } catch (final PersistenceException e) {
      LOGGER.warn("Les objets n'ont pas pu être initialisés.");
      connectionDAO.rollbackAndCloseConnection();
      throw new ServiceException(e);
    } finally {
      connectionDAO.commitAndCloseConnection();
    }
  }

  /**
   * @param companyDAO the companyDAO to set
   */
  public void setCompanyDAO(final ICompanyDAO companyDAO) {
    this.companyDAO = companyDAO;
  }

  /**
   * @param connectionDAO the connectionDAO to set
   */
  public void setConnectionDAO(final IConnectionDAO connectionDAO) {
    this.connectionDAO = connectionDAO;
  }

}

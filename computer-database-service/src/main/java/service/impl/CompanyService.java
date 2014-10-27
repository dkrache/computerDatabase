package service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional(propagation = Propagation.REQUIRED)
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
  @Transactional(readOnly = true)
  public List<Company> readAll() {
    try {
      return companyDAO.readAll();
    } catch (final PersistenceException e) {
      LOGGER.warn("Les objets n'ont pas pu être initialisés.");
      throw new ServiceException(e);
    } finally {
      connectionDAO.closeConnection();
    }
  }

}

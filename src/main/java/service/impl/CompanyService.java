package service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import persistance.exception.PersistenceException;
import persistance.impl.CompanyDAO;
import service.ICompanyService;
import service.exception.ServiceException;
import webapp.dto.CompanyDto;
import binding.ConvertCompanyDtoDo;

/**
 * @author excilys
 *
 */
public class CompanyService implements ICompanyService {
  private static final Logger LOGGER = LoggerFactory.getLogger(CompanyService.class);

  /* (non-Javadoc)
   * @see service.ICompanyService#selectAll()
   */
  @Override
  public List<CompanyDto> selectAll() throws ServiceException {
    try {
      return ConvertCompanyDtoDo.createCompanyDtos(CompanyDAO.INSTANCE.selectAll());
    } catch (final PersistenceException e) {
      LOGGER.warn("Les objets n'ont pas pu être initialisés.");
      throw new ServiceException(e);
    }
  }

}

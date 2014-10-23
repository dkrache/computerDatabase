package service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import persistence.IComputerDAO;
import persistence.IConnectionDAO;
import persistence.exception.PersistenceException;
import service.IComputerService;
import service.exception.ServiceException;
import core.Computer;
import core.Page;

/**
 * @author excilys
 *
 */
@Service("computerService")
public class ComputerService implements IComputerService {
  private static final Logger LOGGER = LoggerFactory.getLogger(ComputerService.class);
  @Autowired
  private IConnectionDAO      connectionDAO;
  @Autowired
  private IComputerDAO        computerDAO;

  /* (non-Javadoc)
   * @see service.IComputerService#selectAll()
   */
  @Override
  public List<Computer> selectAll(final Page page) {
    try {
      final List<Computer> computers = computerDAO.selectAll(page);
      connectionDAO.commitAndCloseConnection();
      return computers;
    } catch (final PersistenceException e) {
      connectionDAO.rollbackAndCloseConnection();
      throw new ServiceException(e);
    }
  }

  /* (non-Javadoc)
   * @see service.IComputerService#selectAll()
   */
  @Override
  public List<Computer> search(final Page page) {
    try {
      final List<Computer> computers = computerDAO.search(page);
      connectionDAO.commitAndCloseConnection();
      return computers;
    } catch (final PersistenceException e) {
      connectionDAO.rollbackAndCloseConnection();
      throw new ServiceException(e);
    }
  }

  /* (non-Javadoc)
   * @see service.IComputerService#select(long)
   */
  @Override
  public Computer select(final long externalIdComputer) {
    Computer computer;
    try {
      computer = computerDAO.select(externalIdComputer);
      connectionDAO.commitAndCloseConnection();
      if (computer != null) {
        return computer;
      } else {
        LOGGER.warn("Impossible to find the computer referenced by " + externalIdComputer);
        return null;
      }
    } catch (final PersistenceException e) {
      connectionDAO.rollbackAndCloseConnection();
      throw new ServiceException(e);
    }
  }

  /* (non-Javadoc)
   * @see service.IComputerService#insert(core.Computer)
   */
  @Override
  public boolean insert(final Computer computer) {

    try {
      // we check if the computer is valid before inserting it in database
      if (validate(computer)) {
        computerDAO.insert(computer);
        connectionDAO.commitAndCloseConnection();
        return true;
      }
    } catch (final PersistenceException e) {
      LOGGER.warn("Error while the insertion");
      connectionDAO.rollbackAndCloseConnection();
      throw new ServiceException(e);
    }
    return false;
  }

  /* (non-Javadoc)
   * @see service.IComputerService#update(core.Computer)
   */
  @Override
  public boolean update(final Computer computer) {

    try {
      //We check if c computer is a valid object before updating it in database.
      if (!validate(computer)) {
        LOGGER.warn("computer invalid for the update");
        return false;
      }
      computerDAO.update(computer);
      connectionDAO.commitAndCloseConnection();
    } catch (final PersistenceException e) {
      LOGGER.warn("Error while the update");
      connectionDAO.rollbackAndCloseConnection();
      throw new ServiceException(e);
    }

    return true;
  }

  /* (non-Javadoc)
   * @see service.IComputerService#delete(long)
   */
  @Override
  public void delete(final long externalIdComputer) {
    // Cas concret :  vérifier que l'utilisateur a les droits de supprimer l'objet. Il n y a pas encore d'utilisateur ici...
    try {
      computerDAO.delete(externalIdComputer);
      connectionDAO.commitAndCloseConnection();
    } catch (final PersistenceException e) {
      connectionDAO.rollbackAndCloseConnection();
      throw new ServiceException(e);
    }
  }

  public boolean validate(final Computer computer) {
    if (StringUtils.isEmpty(computer.getComputerName()) || computer.getComputerName().length() < 4) {
      LOGGER.warn("Name size incorrect");
      return false;
    }

    return true;
  }

  /**
   * @param connectionDAO the connectionDAO to set
   */
  public void setConnectionDAO(final IConnectionDAO connectionDAO) {
    this.connectionDAO = connectionDAO;
  }

  /**
   * @param computerDAO the computerDAO to set
   */
  public void setComputerDAO(final IComputerDAO computerDAO) {
    this.computerDAO = computerDAO;
  }

}

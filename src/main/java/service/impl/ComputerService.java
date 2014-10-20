package service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import persistance.ConnectionDAO;
import persistance.exception.PersistenceException;
import persistance.impl.ComputerDAO;
import service.IComputerService;
import service.exception.ServiceException;
import core.Computer;
import core.Page;

/**
 * @author excilys
 *
 */
public class ComputerService implements IComputerService {
  private static final Logger LOGGER = LoggerFactory.getLogger(ComputerService.class);

  /* (non-Javadoc)
   * @see service.IComputerService#selectAll()
   */
  @Override
  public List<Computer> selectAll(final Page page) {
    try {
      final List<Computer> computers = ComputerDAO.INSTANCE.selectAll(page);
      ConnectionDAO.commitAndCloseConnection();
      return computers;
    } catch (final PersistenceException e) {
      ConnectionDAO.rollbackAndCloseConnection();
      throw new ServiceException(e);
    }
  }

  /* (non-Javadoc)
   * @see service.IComputerService#selectAll()
   */
  @Override
  public List<Computer> search(final Page page) {
    try {
      final List<Computer> computers = ComputerDAO.INSTANCE.search(page);
      ConnectionDAO.commitAndCloseConnection();
      return computers;
    } catch (final PersistenceException e) {
      ConnectionDAO.rollbackAndCloseConnection();
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
      computer = ComputerDAO.INSTANCE.select(externalIdComputer);
      ConnectionDAO.commitAndCloseConnection();
      if (computer != null) {
        return computer;
      } else {
        LOGGER.warn("Impossible to find the computer referenced by " + externalIdComputer);
        return null;
      }
    } catch (final PersistenceException e) {
      ConnectionDAO.rollbackAndCloseConnection();
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
        ComputerDAO.INSTANCE.insert(computer);
        ConnectionDAO.commitAndCloseConnection();
        return true;
      }
    } catch (final PersistenceException e) {
      LOGGER.warn("Error while the insertion");
      ConnectionDAO.rollbackAndCloseConnection();
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
      ComputerDAO.INSTANCE.update(computer);
      ConnectionDAO.commitAndCloseConnection();
    } catch (final PersistenceException e) {
      LOGGER.warn("Error while the update");
      ConnectionDAO.rollbackAndCloseConnection();
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
      ComputerDAO.INSTANCE.delete(externalIdComputer);
      ConnectionDAO.commitAndCloseConnection();
    } catch (final PersistenceException e) {
      ConnectionDAO.rollbackAndCloseConnection();
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
}

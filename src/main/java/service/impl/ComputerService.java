package service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional(propagation = Propagation.REQUIRED)
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
  @Transactional(readOnly = true)
  public List<Computer> readAll(final Page page) {
    try {
      return computerDAO.readAll(page);
    } catch (final PersistenceException e) {
      throw new ServiceException(e);
    } finally {
      connectionDAO.closeConnection();
    }
  }

  /* (non-Javadoc)
   * @see service.IComputerService#selectAll()
   */
  @Override
  @Transactional(readOnly = true)
  public List<Computer> search(final Page page) {
    try {
      return computerDAO.search(page);
    } catch (final PersistenceException e) {
      throw new ServiceException(e);
    } finally {
      connectionDAO.closeConnection();
    }
  }

  /* (non-Javadoc)
   * @see service.IComputerService#select(long)
   */
  @Override
  @Transactional(readOnly = true)
  public Computer read(final long externalIdComputer) {
    Computer computer;
    try {
      computer = computerDAO.read(externalIdComputer);
      if (computer != null) {
        return computer;
      } else {
        LOGGER.warn("Impossible to find the computer referenced by " + externalIdComputer);
        return null;
      }
    } catch (final PersistenceException e) {
      throw new ServiceException(e);
    } finally {
      connectionDAO.closeConnection();
    }
  }

  /* (non-Javadoc)
   * @see service.IComputerService#insert(core.Computer)
   */
  @Override
  public boolean create(final Computer computer) {

    try {
      // we check if the computer is valid before inserting it in database
      if (validate(computer)) {
        computerDAO.create(computer);
        return true;
      }
    } catch (final PersistenceException e) {
      LOGGER.warn("Error while the insertion");
      throw new ServiceException(e);
    } finally {
      connectionDAO.closeConnection();
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
    } catch (final PersistenceException e) {
      LOGGER.warn("Error while the update");
      throw new ServiceException(e);
    } finally {
      connectionDAO.closeConnection();
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
    } catch (final PersistenceException e) {
      throw new ServiceException(e);
    } finally {
      connectionDAO.closeConnection();
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

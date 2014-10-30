package service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import persistence.IComputerDAO;
import persistence.ILoggerDAO;
import service.IComputerService;
import core.Computer;
import core.MyLogger;
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
  private ILoggerDAO          loggerDAO;

  @Autowired
  private IComputerDAO        computerDAO;

  /* (non-Javadoc)
   * @see service.IComputerService#selectAll()
   */
  @Override
  @Transactional(readOnly = true)
  public List<Computer> findAll(final Page page) {
    return computerDAO.findAll();
  }

  /* (non-Javadoc)
   * @see service.IComputerService#selectAll()
   */
  @Override
  @Transactional(readOnly = true)
  public List<Computer> search(final Page page) {
    page.setTotalCount(computerDAO.countSearch('%' + page.getSearchString() + '%'));

    return computerDAO.search('%' + page.getSearchString() + '%',
        new PageRequest(page.getCurrentPage(), page.getLimit()));

  }

  /* (non-Javadoc)
   * @see service.IComputerService#select(long)
   */
  @Override
  @Transactional(readOnly = true)
  public Computer read(final long externalIdComputer) {

    return computerDAO.findOne(externalIdComputer);

  }

  /* (non-Javadoc)
   * @see service.IComputerService#insert(core.Computer)
   */
  @Override
  public boolean create(final Computer computer) {

    // we check if the computer is valid before inserting it in database
    if (validate(computer)) {
      computerDAO.save(computer);

      loggerDAO.save(MyLogger.builder()
          .log("Enregistrement d'un nouvel ordinateur : " + computer.getComputerName()).build());
      return true;
    }
    return false;
  }

  /* (non-Javadoc)
   * @see service.IComputerService#update(core.Computer)
   */
  @Override
  public boolean update(final Computer computer) {

    //We check if c computer is a valid object before updating it in database.
    if (validate(computer)) {
      computerDAO.update(computer.getId(), computer.getComputerName());
      loggerDAO.save(MyLogger.builder()
          .log("MAJ d'un ordinateur(" + computer.getId() + ") : " + computer.getComputerName())
          .build());
      return true;
    }
    LOGGER.warn("computer invalid for the update");
    return false;

  }

  /* (non-Javadoc)
   * @see service.IComputerService#delete(long)
   */
  @Override
  public void delete(final long idComputer) {
    // Cas concret :  vérifier que l'utilisateur a les droits de supprimer l'objet. Il n y a pas encore d'utilisateur ici...
    computerDAO.deleteById(idComputer);
  }

  public boolean validate(final Computer computer) {
    if (StringUtils.isEmpty(computer.getComputerName()) || computer.getComputerName().length() < 3) {
      LOGGER.warn("Name size incorrect");
      return false;
    }
    return true;
  }

}

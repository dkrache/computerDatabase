package service.impl;

import java.text.ParseException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import persistance.exception.PersistenceException;
import persistance.impl.ComputerDAO;
import service.IComputerService;
import service.exception.ServiceException;
import webapp.dto.ComputerDto;
import binding.ConvertComputerDtoDo;
import binding.DateUtils;
import core.Computer;

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
  public List<ComputerDto> selectAll(final int offset) throws ServiceException {
    try {
      return ConvertComputerDtoDo.createComputerDtos(ComputerDAO.INSTANCE.selectAll(offset));
    } catch (final PersistenceException e) {
      throw new ServiceException(e);
    }
  }

  /* (non-Javadoc)
   * @see service.IComputerService#selectAll()
   */
  @Override
  public List<ComputerDto> search(final String name, final int offset) throws ServiceException {
    try {

      return name == null ? null : ConvertComputerDtoDo.createComputerDtos(ComputerDAO.INSTANCE
          .search(name, offset));
    } catch (final PersistenceException e) {
      throw new ServiceException(e);
    }
  }

  /* (non-Javadoc)
   * @see service.IComputerService#select(long)
   */
  @Override
  public ComputerDto select(final long externalIdComputer) throws ServiceException {
    Computer computer;
    try {
      computer = ComputerDAO.INSTANCE.select(externalIdComputer);
      if (computer != null) {
        return ConvertComputerDtoDo.createComputerDto(computer);
      } else {
        LOGGER.warn("L'objet Computer n'a pu être trouvé en base de données.");
        return null;
      }
    } catch (final PersistenceException e) {
      throw new ServiceException(e);
    }
  }

  /* (non-Javadoc)
   * @see service.IComputerService#insert(webapp.dto.ComputerDto)
   */
  @Override
  public boolean insert(final ComputerDto computerDto) throws ServiceException {

    try {
      if (validate(computerDto)) {
        ComputerDAO.INSTANCE.insert(ConvertComputerDtoDo.createComputer(computerDto));
        return true;
      }
    } catch (final ParseException | PersistenceException e) {
      throw new ServiceException(e);
    }
    return false;
  }

  /* (non-Javadoc)
   * @see service.IComputerService#update(webapp.dto.ComputerDto)
   */
  @Override
  public boolean update(final ComputerDto computerDto) throws ServiceException {

    try {
      //On vérifie simplement que l'objet existe en base. 
      if (!validate(computerDto)
          && ComputerDAO.INSTANCE.select(computerDto.getExternalId()) == null) {
        LOGGER.warn("Tentative de MAJ d'un objet n'existe pas ou plus en base de données.");
        return false;
      }
      ComputerDAO.INSTANCE.update(ConvertComputerDtoDo.createComputer(computerDto));
    } catch (final ParseException | PersistenceException e) {
      throw new ServiceException(e);
    }

    return true;
  }

  /* (non-Javadoc)
   * @see service.IComputerService#delete(long)
   */
  @Override
  public void delete(final long externalIdComputer) throws ServiceException {
    // Cas concret :  vérifier que l'utilisateur a les droits de supprimer l'objet. Il n y a pas encore d'utilisateur ici...
    try {
      ComputerDAO.INSTANCE.delete(externalIdComputer);
    } catch (final PersistenceException e) {
      throw new ServiceException(e);
    }
  }

  public boolean validate(final ComputerDto computerDto) {
    if (StringUtils.isEmpty(computerDto.getComputerName())
        || computerDto.getComputerName().length() < 4) {
      LOGGER.warn("La taille du nom de l'ordinateur n'a pas été respecté");
      return false;
    }
    try {
      DateUtils.createStringToCalendar(computerDto.getDiscontinuedDate());
      DateUtils.createStringToCalendar(computerDto.getIntroducedDate());
    } catch (final ParseException e) {
      return false;
    }
    return true;
  }
}

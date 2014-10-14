package service;

import java.util.List;

import service.exception.ServiceException;
import webapp.dto.ComputerDto;

/**
 * @author excilys
 *
 */
public interface IComputerService {
  /**
   * Extraction de tous les computers.
   * @return
   * @throws ServiceException
   */
  List<ComputerDto> selectAll() throws ServiceException;

  /**
   * Extraction d'un Computer référencé par externalIdComputer
   * @param externalIdComputer
   * @return
   * @throws ServiceException
   */
  ComputerDto select(final long externalIdComputer) throws ServiceException;

  /**
   * Insertion d'un nouvel objet computer
   * @param computerDto
   * @return
   * @throws ServiceException
   */
  boolean insert(final ComputerDto computerDto) throws ServiceException;

  /**
   * MAJ d'un objet computer.
   * @param computerDto
   * @return
   * @throws ServiceException
   */
  boolean update(final ComputerDto computerDto) throws ServiceException;

  /**
   * Suppression d'un objet computer
   * @param externalIdComputer
   * @throws ServiceException
   */
  void delete(final long externalIdComputer) throws ServiceException;

  /**
   * Retourne la liste des ordinateurs dont le nom correspond à 'name'
   * @param name
   * @return
   * @throws ServiceException
   */
  List<ComputerDto> search(final String name) throws ServiceException;
}

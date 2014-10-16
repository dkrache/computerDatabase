package service;

import java.util.List;

import service.exception.ServiceException;
import core.Computer;

/**
 * @author excilys
 *
 */
public interface IComputerService {
  /**
   * Extract a List of computers.
   * @return
   * @throws ServiceException
   */
  List<Computer> selectAll(final int offset);

  /**
   * Extract the computer referenced by externalIdComputer
   * @param externalIdComputer
   * @return
   * @throws ServiceException
   */
  Computer select(final long externalIdComputer);

  /**
   * Insertion a new computer.
   * @param computer
   * @return
   * @throws ServiceException
   */
  boolean insert(final Computer computer) throws ServiceException;

  /**
   * Update the computer
   * @param computer
   * @return
   * @throws ServiceException)
   */
  boolean update(final Computer computer) throws ServiceException;

  /**
   * Delete the object referenced by externalIdComputer
   * @param externalIdComputer
   * @throws ServiceException
   */
  void delete(final long externalIdComputer) throws ServiceException;

  /**
   * Get computers whose name is name.
   * @param name
   * @return
   * @throws ServiceException
   */
  List<Computer> search(final String name, final int offset) throws ServiceException;
}

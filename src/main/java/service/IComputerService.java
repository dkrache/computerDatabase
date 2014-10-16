package service;

import java.util.List;

import service.exception.ServiceException;
import core.Computer;
import core.Page;

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
  List<Computer> selectAll(final Page page);

  /**
   * Get computers whose name is name.
   * @param name
   * @return
   * @throws ServiceException
   */
  List<Computer> search(final Page page);

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
  boolean insert(final Computer computer);

  /**
   * Update the computer
   * @param computer
   * @return
   * @throws ServiceException)
   */
  boolean update(final Computer computer);

  /**
   * Delete the object referenced by externalIdComputer
   * @param externalIdComputer
   * @throws ServiceException
   */
  void delete(final long externalIdComputer);

}

package service;

import java.util.List;

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
   */
  List<Computer> readAll(final Page page);

  /**
   * Get computers whose name is name.
   * @param name
   * @return
   */
  List<Computer> search(final Page page);

  /**
   * Extract the computer referenced by externalIdComputer
   * @param externalIdComputer
   * @return
   */
  Computer read(final long externalIdComputer);

  /**
   * Insertion a new computer.
   * @param computer
   * @return
   */
  boolean create(final Computer computer);

  /**
   * Update the computer
   * @param computer
   * @return
   */
  boolean update(final Computer computer);

  /**
   * Delete the object referenced by externalIdComputer
   * @param externalIdComputer
   */
  void delete(final long idComputer);

}

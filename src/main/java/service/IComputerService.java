package service;

import java.util.List;

import org.springframework.stereotype.Service;

import core.Computer;
import core.Page;

/**
 * @author excilys
 *
 */
@Service
public interface IComputerService {
  /**
   * Extract a List of computers.
   * @return
   */
  List<Computer> selectAll(final Page page);

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
  Computer select(final long externalIdComputer);

  /**
   * Insertion a new computer.
   * @param computer
   * @return
   */
  boolean insert(final Computer computer);

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
  void delete(final long externalIdComputer);

}

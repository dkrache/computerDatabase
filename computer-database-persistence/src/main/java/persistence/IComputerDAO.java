package persistence;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import core.Computer;

/**
 * Persiste les objets {link Computer} en base de données
 * @author excilys
 *
 */
public interface IComputerDAO extends MyBaseRepository<Computer, Long> {
  /**
   * Find the computer referenced by the name whose like computerName
   * @param computerName
   * @return
   */
  List<Computer> findByComputerNameLike(String computerName);

  /**
   * Extract all computers
   * @return
   */
  List<Computer> findAll();

  /**
   * Find the computer identified by idComputer
   * @param idComputer
   * @return
   */
  Computer getOne(Long idComputer);

  /**
   * Update the computer identified by idComputer
   * @param idComputer
   * @param name
   */
  @Modifying
  @Query("update computer c set c.computerName = ?2 where c.id = ?1")
  void update(final long idComputer, final String name);

  /**
   * Delete the computer identified by idComputer
   * @param id
   */
  void deleteById(final Long ididComputer);

  /**
   * Search all computer whose the company name or the computer name is like '%name%'
   * @param name
   * @param pageable
   * @return
   */
  @Query("select c from computer c where c.computerName like ?1 or c.company.name like ?1 ")
  List<Computer> search(final String name, final Pageable pageable);

  /**
   * @param name
   * @return the number of row found
   */
  @Query("select count(*) from computer c where c.computerName like ?1 or c.company.name like ?1 ")
  int countSearch(final String name);
}

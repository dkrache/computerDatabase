package persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import persistence.exception.PersistenceException;
import core.Computer;
import core.Page;

@Repository
public interface IComputerDAO {

  /**
   * @param page
   * @return
   * @throws PersistenceException
   */
  List<Computer> selectAll(final Page page) throws PersistenceException;

  /**
   * @param idComputer
   * @return
   * @throws PersistenceException
   */
  Computer select(final long idComputer) throws PersistenceException;

  /**
   * @param computer
   * @throws PersistenceException
   */
  void insert(final Computer computer) throws PersistenceException;

  /**
   * @param computer
   * @throws PersistenceException
   */
  void update(final Computer computer) throws PersistenceException;

  /**
   * @param idComputer
   * @throws PersistenceException
   */
  void delete(final long idComputer) throws PersistenceException;

  /**
   * @param page
   * @return List of computers whose refered to the arguments of page
   * @throws PersistenceException
   */
  List<Computer> search(final Page page) throws PersistenceException;

}

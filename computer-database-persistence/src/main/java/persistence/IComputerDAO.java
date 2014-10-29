package persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import core.Computer;
import core.Page;

@Repository
public interface IComputerDAO {

  /**
   * @param page
   * @return
   */
  List<Computer> readAll(final Page page);

  /**
   * @param idComputer
   * @return
   */
  Computer read(final long idComputer);

  /**
   * @param computer
   */
  void create(final Computer computer);

  /**
   * @param computer
   */
  void update(final Computer computer);

  /**
   * @param idComputer
   */
  void delete(final long idComputer);

  /**
   * @param page
   * @return List of computers whose refered to the arguments of page
   */
  List<Computer> search(final Page page);

}

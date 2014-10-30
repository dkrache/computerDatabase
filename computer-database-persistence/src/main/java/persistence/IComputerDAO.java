package persistence;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import core.Computer;

public interface IComputerDAO extends MyBaseRepository<Computer, Long> {
  List<Computer> findByComputerNameLike(String computerName);

  List<Computer> findAll();

  Computer getOne(Long idComputer);

  @Modifying
  @Query("update computer c set c.computerName = ?2 where c.id = ?1")
  void update(final long idComputer, final String name);

  void deleteById(Long id);

  @Query("select c from computer c where c.computerName like ?1 or c.company.name like ?1 ")
  List<Computer> search(final String name, Pageable pageable);

  @Query("select count(*) from computer c where c.computerName like ?1 or c.company.name like ?1 ")
  int countSearch(final String name);
}

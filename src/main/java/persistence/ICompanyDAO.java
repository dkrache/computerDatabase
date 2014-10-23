package persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import persistence.exception.PersistenceException;
import core.Company;

@Repository
public interface ICompanyDAO {

  /**
   * @return
   * @throws PersistenceException
   */
  List<Company> readAll() throws PersistenceException;

  /**
   * @param idCompany
   * @return
   * @throws PersistenceException
   */
  Company read(final int idCompany) throws PersistenceException;

  /**
   * @param company
   * @throws PersistenceException
   */
  void create(final Company company) throws PersistenceException;

  /**
   * @param company
   * @throws PersistenceException
   */
  void update(final Company company) throws PersistenceException;

  /**
   * @param idCompany
   * @throws PersistenceException
   */
  void delete(final int idCompany) throws PersistenceException;
}

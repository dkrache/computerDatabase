package persistence;

import java.util.List;

import core.Company;

public interface ICompanyDAO {

  /**
   * @return
   */
  List<Company> readAll();

  /**
   * @param idCompany
   * @return
   */
  Company read(final long idCompany);

  /**
   * @param company
   */
  void create(final Company company);

  /**
   * @param company
   */
  void update(final Company company);

  /**
   * @param idCompany
   */
  void delete(final Company company);

}

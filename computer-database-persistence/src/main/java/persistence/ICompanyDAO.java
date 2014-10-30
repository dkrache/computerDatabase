package persistence;

import java.util.List;

import core.Company;

public interface ICompanyDAO extends MyBaseRepository<Company, Long> {

  List<Company> findAll();

}
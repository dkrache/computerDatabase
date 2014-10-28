package persistence.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import persistence.ICompanyDAO;
import persistence.mapper.CompanyRowMapper;
import core.Company;

/**
 * @author excilys
 *
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class CompanyDAO extends JdbcDaoSupport implements ICompanyDAO {
  private static final String SELECT_ALL = "select id, name from company";
  private static final String SELECT     = "select id, name from company where id=?";
  private static final String INSERT     = "insert into company (name) values (?)";
  private static final String UPDATE     = "update company set name=? where id=?";
  private static final String DELETE     = "delete from company where id=?";
  @Autowired
  private CompanyRowMapper    companyRowMapper;

  @Autowired
  private DataSource          dataSource;

  /**
   * @return
   */
  public List<Company> readAll() {
    return getJdbcTemplate().query(SELECT_ALL, companyRowMapper);
  }

  /**
   * @param idCompany
   * @return
   */
  public Company read(final int idCompany) {
    final List<Company> companys = getJdbcTemplate().query(SELECT, companyRowMapper, idCompany);
    return companys.size() > 0 ? companys.get(0) : null;
  }

  /**
   * @param company
   */
  public void create(final Company company) {
    getJdbcTemplate().update(INSERT, company.getName());

  }

  /**
   * @param company
   */
  public void update(final Company company) {
    getJdbcTemplate().update(UPDATE, company.getName(), company.getId());
  }

  /**
   * @param idCompany
   */
  public void delete(final int idCompany) {
    getJdbcTemplate().update(DELETE, idCompany);
  }

  @PostConstruct
  private void initialize() {
    setDataSource(dataSource);
  }
}

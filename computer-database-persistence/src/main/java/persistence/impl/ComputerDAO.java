package persistence.impl;

import java.sql.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import persistence.IComputerDAO;
import persistence.mapper.ComputerRowMapper;
import core.Computer;
import core.MyLogger;
import core.Page;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class ComputerDAO extends JdbcDaoSupport implements IComputerDAO {
  private static final String LEFT_JOIN_COMPANY = " left join company on company.id=computer.company_id ";
  private static final String LIMIT_AND_OFFSET  = " limit ? offset ? ";

  private static final String SELECT_ALL        = "select computer.id, computer.name, introduced, discontinued, company_id from computer ";
  private static final String WHERE_JOIN_CLAUSE = " where name like ? or company_id in (select id from company where name like ?) ";
  private static final String WHERE_CLAUSE      = " where computer.name like ? or company.name like ? ";
  private static final String SELECT_COUNT      = "select count(*) from computer "
                                                    + WHERE_JOIN_CLAUSE;

  private static final String SELECT            = "select id, name, introduced, discontinued, company_id from computer where id=?";
  private static final String INSERT            = "insert into computer (name, introduced,discontinued,company_id) values (?,?,?,?)";
  private static final String UPDATE            = "update computer set name=?, introduced=?, discontinued=?, company_id=? where id=?";
  private static final String DELETE            = "delete from computer where id=?";
  @Autowired
  private DataSource          dataSource;
  @Autowired
  private LoggerDAO           loggerDAO;
  @Autowired
  private ComputerRowMapper   computerRowMapper;

  public ComputerDAO() {

  }

  /**
   * @param page
   * @return
   */
  public List<Computer> readAll(final Page page) {
    page.setTotalCount(count(page));
    return getJdbcTemplate().query(SELECT_ALL + LIMIT_AND_OFFSET, computerRowMapper,
        page.getCurrentPage(), page.getOffset());

  }

  /**
   * @param idComputer
   * @return
   */
  public Computer read(final long idComputer) {
    return getJdbcTemplate().queryForObject(SELECT, computerRowMapper, idComputer);
  }

  /**
   * @param computer
   */
  public void create(final Computer computer) {
    getJdbcTemplate().update(INSERT, computer.getComputerName(),
        new Date(computer.getIntroducedDate().getMillis()),
        new Date(computer.getDiscontinuedDate().getMillis()),
        computer.getCompany() != null ? computer.getCompany().getId() : null);
  }

  /**
   * @param computer
   */
  public void update(final Computer computer) {
    getJdbcTemplate().update(UPDATE, computer.getComputerName(),
        new Date(computer.getIntroducedDate().getMillis()),
        new Date(computer.getDiscontinuedDate().getMillis()), computer.getCompany().getId(),
        computer.getId());

  }

  /**
   * @param idComputer
   */
  public void delete(final long idComputer) {
    getJdbcTemplate().update(DELETE, idComputer);
    loggerDAO.insert(MyLogger.builder()
        .log("Delete of the computer referenced by the id=" + idComputer).build());
  }

  /**
   * @param page
   * @return List of computers whose refered to the arguments of page
   */
  public List<Computer> search(final Page page) {
    final char wildcard = '%';
    page.setTotalCount(count(page));
    return getJdbcTemplate().query(searchWithOrderBy(page), computerRowMapper,
        wildcard + page.getSearchString() + wildcard, wildcard + page.getSearchString() + wildcard,
        page.getLimit(), page.getOffset());
  }

  /**
   * @param page
   * @returnpage
   */
  private int count(final Page page) {
    final char wildcard = '%';
    return getJdbcTemplate().queryForObject(SELECT_COUNT, Integer.class,
        wildcard + page.getSearchString() + wildcard, wildcard + page.getSearchString() + wildcard);
  }

  /**
   * @param page
   * @return
   */
  private String searchWithOrderBy(final Page page) {
    if (page == null) {
      return "";
    }
    final StringBuilder search = new StringBuilder(SELECT_ALL);
    search.append(LEFT_JOIN_COMPANY);
    search.append(WHERE_CLAUSE);

    switch (page.getOrder()) {
      case "name":
        search.append(" order by computer.name ");
        break;
      case "idate":
        search.append(" order by introduced ");
        break;
      case "ddate":
        search.append(" order by discontinued ");
        break;
      case "comp":
        search.append(" order by company.name ");
        break;
      default:
        search.append(" order by computer.id ");
    }
    search.append(getAscendancy(page));
    search.append(LIMIT_AND_OFFSET);

    return search.toString();

  }

  /**
   * @param page
   * @return
   */
  private String getAscendancy(final Page page) {
    return Page.UP.equals(page.getAscendancy()) ? "asc" : "desc";
  }

  @Autowired
  @Qualifier("jdbcTemplate")
  public void setMyJdbcTemplate(final JdbcTemplate jdbcTemplate) {
    super.setJdbcTemplate(jdbcTemplate);
  }

  @PostConstruct
  private void initialize() {
    setDataSource(dataSource);
  }
}

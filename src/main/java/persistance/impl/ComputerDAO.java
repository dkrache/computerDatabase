package persistance.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import persistance.exception.PersistenceException;
import persistance.mapper.ComputerRowMapper;
import persistence.ConnectionDAO;
import core.Computer;
import core.Page;

public enum ComputerDAO {
  INSTANCE;

  private static final String LEFT_JOIN_COMPANY = " left join company on company.id=computer.company_id ";
  private static final String LIMIT_AND_OFFSET  = " limit ? offset ? ";
  private static final String ORDER_BY_ASC      = " order by ? ";
  private static final String ORDER_BY_DESC     = " order by ? DESC ";

  private static final String SELECT_ALL        = "select computer.id, computer.name, introduced, discontinued, company_id from computer ";
  private static final String WHERE_JOIN_CLAUSE = " where name like ? or company_id in (select id from company where name like ?) ";
  private static final String WHERE_CLAUSE      = " where computer.name like ? or company.name like ? ";
  private static final String SELECT_COUNT      = "select count(*) from computer "
                                                    + WHERE_JOIN_CLAUSE;

  private static final String SELECT            = "select id, name, introduced, discontinued, company_id from computer where id=?";
  private static final String INSERT            = "insert into computer (name,introduced,discontinued,company_id) values (?,?,?,?)";
  private static final String UPDATE            = "update computer set name=?, introduced=?, discontinued=?, company_id=? where id=?";
  private static final String DELETE            = "delete from computer where id=?";

  private ComputerDAO() {
    //
  }

  /**
   * @param page
   * @return
   * @throws PersistenceException
   */
  public List<Computer> selectAll(final Page page) throws PersistenceException {
    final Connection connection = ConnectionDAO.getConnection();
    try {
      final PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL
          + LIMIT_AND_OFFSET);
      preparedStatement.setInt(1, page.getCurrentPage());
      preparedStatement.setInt(2, page.getOffset());
      page.setTotalCount(count(page));

      return ComputerRowMapper.convertResultSet(preparedStatement.executeQuery());
    } catch (final SQLException e) {
      throw new PersistenceException(e);
    } finally {
      ConnectionDAO.closeConnection(connection);
    }
  }

  /**
   * @param idComputer
   * @return
   * @throws PersistenceException
   */
  public Computer select(final long idComputer) throws PersistenceException {
    final Connection connection = ConnectionDAO.getConnection();
    try {
      final PreparedStatement preparedStatement = connection.prepareStatement(SELECT);
      preparedStatement.setLong(1, idComputer);
      final List<Computer> computers = ComputerRowMapper.convertResultSet(preparedStatement
          .executeQuery());

      if (!computers.isEmpty()) {
        return computers.get(0);
      }
    } catch (final SQLException e) {
      throw new PersistenceException(e);
    } finally {

      ConnectionDAO.closeConnection(connection);
    }
    return null;
  }

  /**
   * @param computer
   * @throws PersistenceException
   */
  public void insert(final Computer computer) throws PersistenceException {
    final Connection connection = ConnectionDAO.getConnection();
    try {
      final PreparedStatement preparedStatement = connection.prepareStatement(INSERT,
          Statement.RETURN_GENERATED_KEYS);
      preparedStatement.setString(1, computer.getComputerName());
      preparedStatement.setDate(2, new Date(computer.getIntroducedDate().getTime()));
      preparedStatement.setDate(3, new Date(computer.getDiscontinuedDate().getTime()));
      preparedStatement.setLong(4, computer.getCompany() != null ? computer.getCompany().getId()
          : null);
      preparedStatement.execute();
      final ResultSet resultSet = preparedStatement.getGeneratedKeys();
      if (resultSet.next()) {
        computer.setId(resultSet.getLong(1));
      }
    } catch (final SQLException e) {
      throw new PersistenceException(e);
    } finally {
      ConnectionDAO.closeConnection(connection);
    }

  }

  /**
   * @param computer
   * @throws PersistenceException
   */
  public void update(final Computer computer) throws PersistenceException {
    final Connection connection = ConnectionDAO.getConnection();
    try {
      final PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
      preparedStatement.setString(1, computer.getComputerName());
      preparedStatement.setDate(2, new Date(computer.getIntroducedDate().getTime()));
      preparedStatement.setDate(3, new Date(computer.getDiscontinuedDate().getTime()));
      preparedStatement.setLong(4, computer.getCompany() != null ? computer.getCompany().getId()
          : null);
      preparedStatement.setLong(5, computer.getId());
      preparedStatement.execute();
    } catch (final SQLException e) {
      throw new PersistenceException(e);
    } finally {
      ConnectionDAO.closeConnection(connection);
    }
  }

  /**
   * @param idComputer
   * @throws PersistenceException
   */
  public void delete(final long idComputer) throws PersistenceException {
    final Connection connection = ConnectionDAO.getConnection();
    try {
      final PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
      preparedStatement.setLong(1, idComputer);
      preparedStatement.execute();

    } catch (final SQLException e) {
      throw new PersistenceException(e);
    } finally {
      ConnectionDAO.closeConnection(connection);
    }
  }

  /**
   * @param page
   * @return List of computers whose refered to the arguments of page
   * @throws PersistenceException
   */
  public List<Computer> search(final Page page) throws PersistenceException {
    final Connection connection = ConnectionDAO.getConnection();
    final char wildcard = '%';
    try {
      final PreparedStatement preparedStatement;
      preparedStatement = connection.prepareStatement(searchWithOrderBy(page));
      preparedStatement.setString(1, wildcard + page.getSearchString() + wildcard);
      preparedStatement.setString(2, wildcard + page.getSearchString() + wildcard);
      preparedStatement.setInt(3, page.getLimit());
      preparedStatement.setInt(4, page.getOffset());
      page.setTotalCount(count(page));

      return ComputerRowMapper.convertResultSet(preparedStatement.executeQuery());
    } catch (final SQLException e) {
      throw new PersistenceException(e);
    } finally {
      ConnectionDAO.closeConnection(connection);
    }
  }

  /**
   * @param page
   * @returnpage
   * @throws PersistenceException
   */
  private int count(final Page page) throws PersistenceException {
    final Connection connection = ConnectionDAO.getConnection();
    final char wildcard = '%';

    try {
      final PreparedStatement preparedStatement;
      preparedStatement = connection.prepareStatement(SELECT_COUNT);
      preparedStatement.setString(1, wildcard + page.getSearchString() + wildcard);
      preparedStatement.setString(2, wildcard + page.getSearchString() + wildcard);
      final ResultSet rs = preparedStatement.executeQuery();
      if (rs.next()) {
        return rs.getInt(1);
      } else {
        return 0;
      }
    } catch (final SQLException e) {
      throw new PersistenceException(e);
    } finally {
      ConnectionDAO.closeConnection(connection);
    }
  }

  /**
   * @param page
   * @return
   */
  private String searchWithOrderBy(final Page page) {
    if (page == null) {
      return "";
    }
    final StringBuilder SEARCH = new StringBuilder(SELECT_ALL);
    SEARCH.append(LEFT_JOIN_COMPANY);
    SEARCH.append(WHERE_CLAUSE);

    switch (page.getOrder()) {
      case "name":
        SEARCH.append(" order by computer.name ");
        break;
      case "idate":
        SEARCH.append(" order by introduced ");
        break;
      case "ddate":
        SEARCH.append(" order by discontinued ");
        break;
      case "comp":
        SEARCH.append(" order by company.name ");
        break;
      default:
        SEARCH.append(" order by computer.id ");
    }
    SEARCH.append(getAscendancy(page));
    SEARCH.append(LIMIT_AND_OFFSET);

    return SEARCH.toString();

  }

  /**
   * @param page
   * @return
   */
  private String getAscendancy(final Page page) {
    return Page.UP.equals(page.getAscendancy()) ? "asc" : "desc";
  }
}

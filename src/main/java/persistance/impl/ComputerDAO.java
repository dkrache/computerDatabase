package persistance.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.LoggerFactory;

import persistance.exception.PersistenceException;
import persistance.mapper.ComputerRowMapper;
import persistence.ConnectionDAO;
import core.Computer;
import core.Logger;
import core.Page;

public enum ComputerDAO {
  INSTANCE;
  private int                           nombreDeSelect    = 0;
  private static final org.slf4j.Logger LOGGER            = LoggerFactory
                                                              .getLogger(ComputerDAO.class);
  private static final String           LEFT_JOIN_COMPANY = " left join company on company.id=computer.company_id ";
  private static final String           LIMIT_AND_OFFSET  = " limit ? offset ? ";

  private static final String           SELECT_ALL        = "select computer.id, computer.name, introduced, discontinued, company_id from computer ";
  private static final String           WHERE_JOIN_CLAUSE = " where name like ? or company_id in (select id from company where name like ?) ";
  private static final String           WHERE_CLAUSE      = " where computer.name like ? or company.name like ? ";
  private static final String           SELECT_COUNT      = "select count(*) from computer "
                                                              + WHERE_JOIN_CLAUSE;

  private static final String           SELECT            = "select id, name, introduced, discontinued, company_id from computer where id=?";
  private static final String           INSERT            = "insert into computer (name,introduced,discontinued,company_id) values (?,?,?,?)";
  private static final String           UPDATE            = "update computer set name=?, introduced=?, discontinued=?, company_id=? where id=?";
  private static final String           DELETE            = "delete from computer where id=?";

  private static final int              SECONDE           = 60;

  private ComputerDAO() {
    final Timer timer = new Timer();
    timer.scheduleAtFixedRate(new TimerTask() {
      @Override
      public void run() {
        try {
          LoggerDAO.INSTANCE.insert(Logger.builder()
              .log(nombreDeSelect + " select those last " + SECONDE + " seconds").build());
          ConnectionDAO.getConnection().commit();
        } catch (final PersistenceException | SQLException e) {
          LOGGER.warn("error while inserting logger with the timer", e);
        }
        nombreDeSelect = 0;

      }
    }, new Date(System.currentTimeMillis()), 1000 * SECONDE);
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
      //Initialization of variables
      preparedStatement.setInt(1, page.getCurrentPage());
      preparedStatement.setInt(2, page.getOffset());
      page.setTotalCount(count(page));

      // pour les logs
      nombreDeSelect++;

      return ComputerRowMapper.convertResultSet(preparedStatement.executeQuery());

    } catch (final SQLException e) {
      LoggerDAO.INSTANCE.insert(Logger.builder().log("Error while getting computers").exception(e)
          .build());
      throw new PersistenceException(e);
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
      nombreDeSelect++;
      if (!computers.isEmpty()) {
        return computers.get(0);
      }
    } catch (final SQLException e) {
      LoggerDAO.INSTANCE.insert(Logger.builder()
          .log("Error while getting the computer where id is " + idComputer).exception(e).build());

      throw new PersistenceException(e);
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
      if (computer.getCompany() == null) {
        preparedStatement.setLong(4, 0);
      } else {
        preparedStatement.setLong(4, computer.getCompany().getId());
      }
      preparedStatement.execute();
      final ResultSet resultSet = preparedStatement.getGeneratedKeys();
      if (resultSet.next()) {
        computer.setId(resultSet.getLong(1));
        connection.commit();
      }
      LoggerDAO.INSTANCE.insert(Logger.builder().log("Insertion of computer").build());
    } catch (final SQLException e) {
      LoggerDAO.INSTANCE.insert(Logger.builder().log("Error while inserting a computer")
          .exception(e).build());

      throw new PersistenceException(e);
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
      if (computer.getCompany() == null) {
        preparedStatement.setLong(4, 0);
      } else {
        preparedStatement.setLong(4, computer.getCompany().getId());
      }
      preparedStatement.setLong(5, computer.getId());
      preparedStatement.execute();
      LoggerDAO.INSTANCE.insert(Logger.builder().log("MAJ computer " + computer.getId()).build());
      connection.commit();
    } catch (final SQLException e) {
      LoggerDAO.INSTANCE.insert(Logger.builder().log("Error while updating computers").exception(e)
          .build());
      throw new PersistenceException(e);
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
      connection.commit();
      LoggerDAO.INSTANCE.insert(Logger.builder()
          .log("Delete of the computer referenced by the id=" + idComputer).build());
    } catch (final SQLException e) {
      LoggerDAO.INSTANCE.insert(Logger.builder()
          .log("Error while deleting computer where his id=" + idComputer).exception(e).build());
      throw new PersistenceException(e);
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
      nombreDeSelect++;
      return ComputerRowMapper.convertResultSet(preparedStatement.executeQuery());
    } catch (final SQLException e) {
      LoggerDAO.INSTANCE.insert(Logger.builder().log("Error while searching computers")
          .exception(e).build());
      throw new PersistenceException(e);
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
}

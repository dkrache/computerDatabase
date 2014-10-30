package persistence.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import persistence.IComputerDAO;
import persistence.ILoggerDAO;

import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPADeleteClause;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.jpa.impl.JPAUpdateClause;
import com.mysema.query.types.OrderSpecifier;

import core.Computer;
import core.MyLogger;
import core.Page;
import core.QComputer;

@Repository("computerDAO")
@Transactional(propagation = Propagation.MANDATORY)
public class ComputerDAO implements IComputerDAO {

  private static final char WILDCARD = '%';
  @PersistenceContext(unitName = "persistenceUnit")
  private EntityManager     entityManager;
  @Autowired
  private ILoggerDAO        loggerDAO;

  public ComputerDAO() {

  }

  @Override
  public void create(final Computer computer) {

    entityManager.persist(computer);

  }

  @Override
  @Transactional
  public List<Computer> readAll(final Page page) {
    final JPQLQuery query = new JPAQuery(entityManager);
    page.setTotalCount(query.from(QComputer.computer).count());
    return query.from(QComputer.computer).list(QComputer.computer);

  }

  @Override
  @Transactional
  public Computer read(final long idComputer) {
    final JPQLQuery query = new JPAQuery(entityManager);
    return query.from(QComputer.computer).where(QComputer.computer.id.eq(idComputer))
        .uniqueResult(QComputer.computer);
  }

  @Override
  @Transactional
  public void update(final Computer computer) {
    final QComputer qcomputer = QComputer.computer;
    new JPAUpdateClause(entityManager, qcomputer).where(qcomputer.id.eq(computer.getId()))
        .set(qcomputer.computerName, computer.getComputerName())
        .set(qcomputer.discontinuedDate, computer.getDiscontinuedDate())
        .set(qcomputer.introducedDate, computer.getIntroducedDate())
        .set(qcomputer.company, computer.getCompany()).execute();

    loggerDAO.insert(MyLogger.builder().log("mise à jour de : " + qcomputer.toString()).build());
  }

  @Override
  @Transactional
  public void delete(final long idComputer) {
    final QComputer computer = QComputer.computer;
    new JPADeleteClause(entityManager, computer).where(computer.id.eq(idComputer)).execute();
  }

  /* (non-Javadoc)
   * @see persistence.IComputerDAO#search(core.Page)
   */
  @Override
  @Transactional
  public List<Computer> search(final Page page) {
    final JPQLQuery query = new JPAQuery(entityManager);
    page.setTotalCount(count(page));

    return query
        .from(QComputer.computer)
        .where(QComputer.computer.computerName.like(WILDCARD + page.getSearchString() + WILDCARD))
        .where(
            QComputer.computer.company.name.like(WILDCARD + page.getSearchString() + WILDCARD).or(
                QComputer.computer.company.name.isNull()))
        .orderBy(searchWithOrderBy(page, QComputer.computer)).limit(page.getLimit())
        .offset(page.getOffset()).list(QComputer.computer);

  }

  private long count(final Page page) {
    final JPQLQuery query = new JPAQuery(entityManager);
    return query
        .from(QComputer.computer)
        .where(QComputer.computer.computerName.like(WILDCARD + page.getSearchString() + WILDCARD))
        .where(
            QComputer.computer.company.name.like(WILDCARD + page.getSearchString() + WILDCARD).or(
                QComputer.computer.company.name.isNull()))
        .orderBy(searchWithOrderBy(page, QComputer.computer)).limit(page.getLimit())
        .offset(page.getOffset()).count();
  }

  /**
   * @param page
   * @param computer
   * @return
   */
  private OrderSpecifier<?> searchWithOrderBy(final Page page, final QComputer computer) {
    final boolean ascendant = "asc".equals(getAscendancy(page));
    switch (page.getOrder()) {
      case "name":
        return ascendant ? computer.computerName.asc() : computer.computerName.desc();
      case "idate":
        return ascendant ? computer.introducedDate.asc() : computer.introducedDate.desc();
      case "ddate":
        return ascendant ? computer.discontinuedDate.asc() : computer.discontinuedDate.desc();
      case "comp":
        return ascendant ? computer.company.name.asc() : computer.company.name.desc();
      default:
        return computer.id.asc();
    }

  }

  /**
  * @param page
  * @return
  */
  private String getAscendancy(final Page page) {
    return Page.UP.equals(page.getAscendancy()) ? "asc" : "desc";
  }

}

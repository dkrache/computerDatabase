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
import core.Computer;
import core.MyLogger;
import core.Page;

@Repository("computerDAO")
@Transactional(propagation = Propagation.MANDATORY)
public class ComputerDAO implements IComputerDAO {

  @PersistenceContext(unitName = "persistenceUnit")
  private EntityManager entityManager;
  @Autowired
  private ILoggerDAO     loggerDAO;

  public ComputerDAO() {

  }

  @Override
  public void create(final Computer computer) {

    entityManager.persist(computer);

  }

  @SuppressWarnings("unchecked")
  @Override
  @Transactional
  public List<Computer> readAll(final Page page) {

    return entityManager.createQuery("FROM computer").getResultList();

  }

  @Override
  @Transactional
  public Computer read(final long idComputer) {

    return (Computer) entityManager.createQuery("SELECT p FROM computer p where p.id = :id")
        .setParameter("id", idComputer).getSingleResult();

  }

  @Override
  @Transactional
  public void update(final Computer computer) {
    entityManager.merge(computer);
    
    loggerDAO.insert(MyLogger.builder().log("mise à jour de : " + computer.toString()).build());
  }

  @Override
  @Transactional
  public void delete(final long idComputer) {
    entityManager.createQuery("delete from computer c where c.id = :id")
        .setParameter("id", idComputer).executeUpdate();
  }

  @SuppressWarnings("unchecked")
  @Override
  @Transactional
  public List<Computer> search(final Page page) {
    final char wildcard = '%';

    return entityManager.createQuery(searchWithOrderBy(page))
        .setParameter("cnom", wildcard + page.getSearchString() + wildcard)
        .setParameter("pnom", wildcard + page.getSearchString() + wildcard)
        .setFirstResult(page.getOffset()).setMaxResults(page.getLimit()).getResultList();

  }

  /**
  * @param page
  * @return
  */
  private String searchWithOrderBy(final Page page) {
    if (page == null) {
      return "";
    }
    final StringBuilder search = new StringBuilder(
        "SELECT p FROM computer p  where p.computerName like :pnom or p.company.name like :cnom ");
    switch (page.getOrder()) {
      case "name":
        search.append(" order by p.computerName ");
        break;
      case "idate":
        search.append(" order by p.introducedDate ");
        break;
      case "ddate":
        search.append(" order by p.discontinuedDate ");
        break;
      case "comp":
        search.append(" order by p.company.name ");
        break;
      default:
        search.append(" order by p.id ");
    }
    search.append(getAscendancy(page));
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

package persistence.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

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
  private ILoggerDAO    loggerDAO;

  public ComputerDAO() {

  }

  @Override
  public void create(final Computer computer) {

    entityManager.persist(computer);

  }

  @Override
  @Transactional
  public List<Computer> readAll(final Page page) {
    final CriteriaBuilder criteria = entityManager.getCriteriaBuilder();
    final CriteriaQuery<Computer> criteriaQuery = criteria.createQuery(Computer.class);
    final Root<Computer> root = criteriaQuery.from(Computer.class);
    criteriaQuery.select(root);
    final TypedQuery<Computer> query = entityManager.createQuery(criteriaQuery);
    return query.getResultList();

  }

  @Override
  @Transactional
  public Computer read(final long idComputer) {
    final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    final CriteriaQuery<Computer> criteriaQuery = criteriaBuilder.createQuery(Computer.class);
    final Root<Computer> computer = criteriaQuery.from(Computer.class);
    criteriaQuery.select(computer);
    criteriaQuery.where(criteriaBuilder.equal(computer.<Integer> get("id"), idComputer));
    final TypedQuery<Computer> query = entityManager.createQuery(criteriaQuery);
    final List<Computer> computers = query.getResultList();
    if (computers.size() > 0) {
      return computers.get(0);
    }
    return null;
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

  @Override
  @Transactional
  public List<Computer> search(final Page page) {
    final char wildcard = '%';
    page.setTotalCount(count(page));

    final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    final CriteriaQuery<Computer> criteriaQuery = criteriaBuilder.createQuery(Computer.class);
    final Root<Computer> root = criteriaQuery.from(Computer.class);

    criteriaQuery.select(root);
    root.fetch("company");
    criteriaQuery.where(criteriaBuilder.like(root.<String> get("computerName"),
        wildcard + page.getSearchString() + wildcard));

    criteriaQuery.where(criteriaBuilder.like(root.<String> get("company").<String> get("name"),
        wildcard + page.getSearchString() + wildcard));
    criteriaQuery.orderBy(searchWithOrderBy(page, criteriaBuilder, root));

    final TypedQuery<Computer> query = entityManager.createQuery(criteriaQuery);
    return query.setFirstResult(page.getOffset()).setMaxResults(page.getLimit()).getResultList();

    //   
    //    
    //    return entityManager.createQuery(searchWithOrderBy(page))
    //        .setParameter("nom", wildcard + page.getSearchString() + wildcard)
    //        .setFirstResult(page.getOffset()).setMaxResults(page.getLimit()).getResultList();

  }

  private long count(final Page page) {
    final char wildcard = '%';
    return (long) entityManager
        .createQuery(
            "select count(*) from computer  where computerName like :nom or company.id in (select id from company where name like :nom)")
        .setParameter("nom", wildcard + page.getSearchString() + wildcard).getSingleResult();

  }

  /**
  * @param page
  * @return
  */
  private Order searchWithOrderBy(final Page page, final CriteriaBuilder criteriaBuilder,
      final Root<Computer> root) {
    final Path<Computer> path;
    switch (page.getOrder()) {
      case "name":

        path = root.get("computerName");
        break;
      case "idate":
        path = root.get("introducedDate");
        break;
      case "ddate":
        path = root.get("discontinuedDate");
        break;
      case "comp":
        path = root.get("company.name");
        break;
      default:
        path = root.get("id");
    }
    return "asc".equals(getAscendancy(page)) ? criteriaBuilder.asc(path) : criteriaBuilder
        .desc(path);
  }

  /**
  * @param page
  * @return
  */
  private String getAscendancy(final Page page) {
    return Page.UP.equals(page.getAscendancy()) ? "asc" : "desc";
  }

}

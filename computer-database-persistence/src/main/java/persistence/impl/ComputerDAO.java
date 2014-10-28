package persistence.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import persistence.IComputerDAO;
import core.Computer;
import core.Page;

@Repository("computerDAO")
@Transactional(propagation = Propagation.MANDATORY)
public class ComputerDAO implements IComputerDAO {

  @PersistenceContext(unitName = "persistenceUnit")
  private EntityManager entityManager;

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
  }

  @Override
  @Transactional
  public void delete(final Computer computer) {
    entityManager.remove(computer);
  }

  @Override
  @Transactional
  public List<Computer> search(final Page page) {
    final char wildcard = '%';
    return entityManager.createQuery(searchWithOrderBy(page))
        .setParameter("name", wildcard + page.getSearchString() + wildcard)
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
        "SELECT p FROM core.Computer p  where p.name like :nom or p.company_id in (select c from core.Company c where c.name like :nom) ");
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

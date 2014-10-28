package persistence.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import persistence.ICompanyDAO;
import core.Company;

/**
 * @author excilys
 *
 */
@Repository("companyDAO")
@Transactional(propagation = Propagation.MANDATORY)
public class CompanyDAO implements ICompanyDAO {
  @PersistenceContext(unitName = "persistenceUnit")
  private EntityManager entityManager;

  @Override
  public void create(final Company company) {

    entityManager.persist(company);

  }

  @SuppressWarnings("unchecked")
  @Override
  @Transactional
  public List<Company> readAll() {

    return entityManager.createQuery("FROM core.Company").getResultList();

  }

  @Override
  @Transactional
  public Company read(final long idCompany) {
    Company result = null;
    final EntityTransaction transaction = entityManager.getTransaction();
    try {
      transaction.begin();
      result = (Company) entityManager.createQuery("SELECT p FROM company p where p.id = :id")
          .setParameter("id", idCompany).getSingleResult();
      transaction.commit();
    } catch (final Exception e) {
      e.printStackTrace();
      transaction.rollback();
    }
    return result;
  }

  @Override
  @Transactional
  public void update(final Company company) {
    final EntityTransaction transaction = entityManager.getTransaction();
    try {
      transaction.begin();
      entityManager.merge(company);
      transaction.commit();
    } catch (final Exception e) {
      e.printStackTrace();
      transaction.rollback();
    }

  }

  @Override
  @Transactional
  public void delete(final Company company) {
    final EntityTransaction transaction = entityManager.getTransaction();
    try {
      transaction.begin();
      entityManager.remove(company);
      transaction.commit();
    } catch (final Exception e) {
      e.printStackTrace();
      transaction.rollback();
    }
  }

}

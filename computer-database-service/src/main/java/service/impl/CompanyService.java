package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import persistence.ICompanyDAO;
import service.ICompanyService;
import core.Company;

/**
 * @author excilys
 *
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class CompanyService implements ICompanyService {
  @Autowired
  private ICompanyDAO companyDAO;

  /* (non-Javadoc)
   * @see service.ICompanyService#selectAll()
   */
  @Override
  @Transactional(readOnly = true)
  public List<Company> readAll() {
    return companyDAO.findAll();
  }

}

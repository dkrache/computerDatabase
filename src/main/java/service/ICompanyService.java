package service;

import java.util.List;

import org.springframework.stereotype.Service;

import core.Company;

/**
 * @author excilys
 *
 */
@Service
public interface ICompanyService {

  /**
   * Get all the Companys
   * @return
   */
  List<Company> selectAll();

}

package binding;

import java.util.ArrayList;
import java.util.List;

import webapp.dto.CompanyDto;
import core.Company;

/**
 * @author excilys
 *
 */
public final class ConvertCompanyDtoDo {

  /**
   * Classe utilitaire
   */
  private ConvertCompanyDtoDo() {
    super();
  }

  /**
   * Converti un Company en CompanyDto
   * @param company
   * @return
   */
  public static CompanyDto createCompanyDto(final Company company) {
    if (company == null) {
      return null;
    }
    final CompanyDto companyDto = new CompanyDto();
    companyDto.setExternalId(company.getId());
    companyDto.setName(company.getName());
    return companyDto;
  }

  /**
   * Comverti un CompanyDto en Company
   * @param companyDto
   * @return
   */
  public static Company createCompany(final CompanyDto companyDto) {
    if (companyDto == null) {
      return null;
    }
    return Company.builder(companyDto.getName()).id(companyDto.getExternalId()).build();
  }

  /**
   * Converti une List<Company> en List<CompanyDto>
   * @param companys
   * @return
   */
  public static List<CompanyDto> createCompanyDtos(final List<Company> companys) {
    final List<CompanyDto> companyDtos = new ArrayList<>();
    for (Company company : companys) {
      companyDtos.add(createCompanyDto(company));
    }
    return companyDtos;
  }
}

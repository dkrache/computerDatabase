package binding;

import java.util.ArrayList;
import java.util.List;

import core.Company;
import core.dto.CompanyDto;

/**
 * @author excilys
 *
 */
public final class CompanyMapper {

  /**
   * Classe utilitaire
   */
  private CompanyMapper() {
    super();
  }

  /**
   * Converti un Company en CompanyDto
   * @param company
   * @return
   */
  public static CompanyDto toDto(final Company company) {
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
  public static Company fromDto(final CompanyDto companyDto) {
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
  public static List<CompanyDto> toDto(final List<Company> companys) {
    final List<CompanyDto> companyDtos = new ArrayList<>();
    for (Company company : companys) {
      companyDtos.add(toDto(company));
    }
    return companyDtos;
  }
}

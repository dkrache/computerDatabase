package binding;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import webapp.dto.CompanyDto;

@Component
public class StringToCompanyDto implements Converter<String, CompanyDto> {

  @Override
  public CompanyDto convert(final String externalId) {
    if (StringUtils.isNumeric(externalId)) {
      final int idCompany = Integer.parseInt(externalId);
      if (idCompany > 0) {
        final CompanyDto companyDto = new CompanyDto();
        companyDto.setExternalId(idCompany);
        return companyDto;
      }
    }
    return null;
  }
}

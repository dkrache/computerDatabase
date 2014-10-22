package binding;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import webapp.dto.ComputerDto;
import core.Computer;

/**
 * @author excilys
 *
 */
public final class ComputerMapper {

  /**
   * Classe utilitaire
   */
  private ComputerMapper() {
    super();
  }

  /**
   * @param computer
   * @return
   */
  public static ComputerDto toDto(final Computer computer) {
    final ComputerDto computerDto = new ComputerDto();
    if (computer == null) {
      return null;
    }
    computerDto.setExternalId(computer.getId());
    computerDto.setComputerName(computer.getComputerName());
    computerDto.setDiscontinuedDate(DateUtils.createDateToString(computer.getDiscontinuedDate()));
    computerDto.setIntroducedDate(DateUtils.createDateToString(computer.getIntroducedDate()));
    computerDto.setCompanyDto(CompanyMapper.toDto(computer.getCompany()));
    return computerDto;
  }

  /**
   * @param computerDto
   * @return
   * @throws ParseException 
   */
  public static Computer fromDto(final ComputerDto computerDto) throws ParseException {
    return Computer.builder(computerDto.getComputerName())
        .company(CompanyMapper.fromDto(computerDto.getCompanyDto()))
        .id(computerDto.getExternalId())
        .discontinuedDate(DateUtils.createStringToDate(computerDto.getDiscontinuedDate()))
        .introducedDate(DateUtils.createStringToDate(computerDto.getIntroducedDate())).build();

  }

  public static List<ComputerDto> toDto(final List<Computer> computers) {
    final List<ComputerDto> computerDtos = new ArrayList<>();
    for (final Computer computer : computers) {
      computerDtos.add(toDto(computer));
    }
    return computerDtos;
  }
}
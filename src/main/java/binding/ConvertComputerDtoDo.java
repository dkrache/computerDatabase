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
public final class ConvertComputerDtoDo {

  /**
   * Classe utilitaire
   */
  private ConvertComputerDtoDo() {
    super();
  }

  /**
   * @param computer
   * @return
   */
  public static ComputerDto createComputerDto(final Computer computer) {
    final ComputerDto computerDto = new ComputerDto();
    computerDto.setExternalId(computer.getId());
    computerDto.setComputerName(computer.getComputerName());
    computerDto.setDiscontinuedDate(DateUtils.createDateToString(computer.getDiscontinuedDate()));
    computerDto.setIntroducedDate(DateUtils.createDateToString(computer.getIntroducedDate()));
    computerDto.setCompanyDto(ConvertCompanyDtoDo.createCompanyDto(computer.getCompany()));
    return computerDto;
  }

  /**
   * @param computerDto
   * @return
   * @throws ParseException 
   */
  public static Computer createComputer(final ComputerDto computerDto) throws ParseException {
    final Computer computer = new Computer();
    computer.setId(computerDto.getExternalId());
    computer.setComputerName(computerDto.getComputerName());
    computer.setDiscontinuedDate(DateUtils.createStringToDate(computerDto.getDiscontinuedDate()));
    computer.setIntroducedDate(DateUtils.createStringToDate(computerDto.getIntroducedDate()));
    computer.setCompany(ConvertCompanyDtoDo.createCompany(computerDto.getCompanyDto()));
    return computer;
  }

  public static List<ComputerDto> createComputerDtos(final List<Computer> computers) {
    final List<ComputerDto> computerDtos = new ArrayList<>();
    for (final Computer computer : computers) {
      computerDtos.add(createComputerDto(computer));
    }
    return computerDtos;
  }
}

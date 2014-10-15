package test;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import persistance.exception.PersistenceException;
import persistance.impl.CompanyDAO;
import persistance.impl.ComputerDAO;
import core.Company;
import core.Computer;

public class TestComputerDAO {
  private Computer computer;
  private Company  company;

  @Before
  public void setUp() throws PersistenceException {
    company = CompanyDAO.INSTANCE.select(1);
    computer = Computer.builder("test").company(company)
        .discontinuedDate(new Date(System.currentTimeMillis()))
        .introducedDate(new Date(System.currentTimeMillis())).build();
  }

  @Test
  public void computerCrud() throws PersistenceException {
    final List<Computer> computersALaBase = ComputerDAO.INSTANCE.selectAll();
    ComputerDAO.INSTANCE.insert(computer);
    List<Computer> computersMAJ = ComputerDAO.INSTANCE.selectAll();
    assertEquals(computersALaBase.size() + 1, computersMAJ.size());
    Computer computerRetreived = ComputerDAO.INSTANCE.select(computer.getId());

    assertEquals(computerRetreived.getComputerName(), computer.getComputerName());
    assertEquals(computerRetreived.getCompany().getId(), computer.getCompany().getId());

    assertEquals(computerRetreived.getId(), computer.getId());
    // FIXME DKR : Probleme de conversion en BDD au niveau des heures 
    assertEquals(computerRetreived.getDiscontinuedDate(), computer.getDiscontinuedDate());
    assertEquals(computerRetreived.getIntroducedDate(), computer.getIntroducedDate());
    computerRetreived = Computer.builder("testUpdated").company(CompanyDAO.INSTANCE.select(2))
        .discontinuedDate(new Date(System.currentTimeMillis()))
        .introducedDate(new Date(System.currentTimeMillis())).build();

    ComputerDAO.INSTANCE.update(computerRetreived);
    computersMAJ = ComputerDAO.INSTANCE.selectAll();
    assertEquals(computersALaBase.size() + 1, computersMAJ.size());

    ComputerDAO.INSTANCE.delete(computer.getId());
    computersMAJ = ComputerDAO.INSTANCE.selectAll();
    assertEquals(computersALaBase.size(), computersMAJ.size());
  }
}

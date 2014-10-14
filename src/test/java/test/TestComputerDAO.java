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
    computer = new Computer();
    computer.setCompany(company);
    computer.setComputerName("test");
    computer.setDiscontinuedDate(new Date(System.currentTimeMillis()));
    computer.setIntroducedDate(new Date(System.currentTimeMillis()));
  }

  @Test
  public void computerCrud() throws PersistenceException {
    final List<Computer> computersALaBase = ComputerDAO.INSTANCE.selectAll();
    ComputerDAO.INSTANCE.insert(computer);
    List<Computer> computersMAJ = ComputerDAO.INSTANCE.selectAll();
    assertEquals(computersALaBase.size() + 1, computersMAJ.size());
    final Computer computerRetreived = ComputerDAO.INSTANCE.select(computer.getId());

    assertEquals(computerRetreived.getComputerName(), computer.getComputerName());
    assertEquals(computerRetreived.getCompany().getId(), computer.getCompany().getId());

    assertEquals(computerRetreived.getId(), computer.getId());
    // FIXME DKR : Probleme de conversion en BDD au niveau des heures 
    assertEquals(computerRetreived.getDiscontinuedDate(), computer.getDiscontinuedDate());
    assertEquals(computerRetreived.getIntroducedDate(), computer.getIntroducedDate());

    computerRetreived.setComputerName("testUpdated");
    computerRetreived.setIntroducedDate(new Date(System.currentTimeMillis()));
    computerRetreived.setDiscontinuedDate(new Date(System.currentTimeMillis()));
    computerRetreived.setCompany(CompanyDAO.INSTANCE.select(2));

    ComputerDAO.INSTANCE.update(computerRetreived);
    computersMAJ = ComputerDAO.INSTANCE.selectAll();
    assertEquals(computersALaBase.size() + 1, computersMAJ.size());

    ComputerDAO.INSTANCE.delete(computer.getId());
    computersMAJ = ComputerDAO.INSTANCE.selectAll();
    assertEquals(computersALaBase.size(), computersMAJ.size());
  }
}

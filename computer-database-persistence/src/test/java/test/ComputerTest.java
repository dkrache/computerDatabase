package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import persistence.IComputerDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/persistenceApplicationContext.xml")
public class ComputerTest {
  @Autowired
  private IComputerDAO computerDAO;

  @Test
  public void test() {
    System.out.println(computerDAO.read(634).getCompany());

  }

}

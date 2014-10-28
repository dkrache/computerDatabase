package test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import persistence.IComputerDAO;
import core.Page;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/persistenceApplicationContext.xml")
public class ComputerTest {
  @Autowired
  private IComputerDAO computerDAO;

  @Test
  public void test() {
    assertTrue(computerDAO.readAll(Page.builder().build()).size() > 0);
    System.out.println(computerDAO.readAll(Page.builder().build()).size());
  }

}

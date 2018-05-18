package ta.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;


public class TryTestNG_T2 {
  private static final Logger logger = LoggerFactory.getLogger(TryTestNG_T2.class);

  @BeforeClass
  public void setup() {
    logger.info("I am in Setup");
  }

  @Test
  public void test() {
    assertTrue(false);
    logger.info("I am in Test");
  }

  @AfterClass
  public void tearDown() {
    logger.info("I am in tearDown");
  }
}

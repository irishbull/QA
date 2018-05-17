package ta.testcases;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class TryTestNG_T1 {
  private static final Logger logger = LoggerFactory.getLogger(TryTestNG_T1.class);

  @BeforeClass
  public void setup() {
    logger.info("I am in Setup");
  }

  @Test
  public void test() {
    logger.info("I am in Test");
  }

  @AfterClass
  public void tearDown() {
    logger.info("I am in tearDown");
  }
}

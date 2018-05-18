package ta.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import ta.driver.SeleniumDriver;


public abstract class BaseTest {

  private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

  @Parameters({"browser"})
  @BeforeClass
  public void setUp(@Optional("chrome") String browser) {
    SeleniumDriver.getInstance().setDriver(browser);
    logger.info("Set up");
  }

  @AfterClass
  public static void tearDown() {
    SeleniumDriver.getInstance().getDriver().close();
    logger.info("Tear down");
  }
}

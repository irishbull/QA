package ta.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import ta.driver.SeleniumDriver;


public abstract class BaseTest {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  @Parameters({"browser"})
  @BeforeClass
  public void setUp(@Optional("chrome") String browser) {
    logger.info("Set up");
    SeleniumDriver.getInstance().setDriver(browser);
    logger.info("Driver info {}", SeleniumDriver.getInstance().toString());
  }

  @AfterClass
  public void tearDown() {
    logger.info("Tear down");
    logger.info("Driver info {}", SeleniumDriver.getInstance().toString());
    SeleniumDriver.getInstance().getDriver().close(); // close the actual window
  }
}

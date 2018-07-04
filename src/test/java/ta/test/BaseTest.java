package ta.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import ta.driver.SeleniumDriver;
import ta.utilities.ReadPropertiesFile;


public abstract class BaseTest {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  @BeforeSuite
  public void suiteSetup() {
    logger.info("Set up");

    String environment = ReadPropertiesFile.getProperty("environment");
    Assert.assertNotNull(environment, "Required property 'environment' not found");
    logger.info("Environmente = [{}]", environment);

    String browser = System.getProperty("browser");
    Assert.assertNotNull(browser, "Required parameter 'Browser' is missing");
    /* debug locally
     String browser = System.getProperty("browser", "chrome");
     logger.info("Browser [{}]", browser);
    */
    SeleniumDriver.getInstance().setDriver(browser);
    logger.info("Driver instance {}", SeleniumDriver.getInstance().toString());

    SeleniumDriver.getInstance().getDriver().manage().window().maximize();
  }

  @AfterSuite
  public void suiteTeardown() {
    logger.info("Tear down");
    logger.info("Driver instance {}", SeleniumDriver.getInstance().toString());
    SeleniumDriver.getInstance().getDriver().quit();
  }
}

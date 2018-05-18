package ta.test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import ta.driver.SeleniumDriver;


public abstract class BaseTest {

  @Parameters({"browser"})
  @BeforeClass
  public void setUp(@Optional("chrome") String browser) {
    SeleniumDriver.getInstance().setDriver(browser);
  }

  @AfterClass
  public static void tearDown() {
    SeleniumDriver.getInstance().getDriver().close();
  }
}

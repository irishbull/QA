package ta.config;

import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import java.util.concurrent.TimeUnit;
import ta.utilities.Constants;

/**
 * <strong>BaseTest</strong> handles setup and teardown of WebDriver.
 * 
 * @author dmonaco
 */
public class BaseTest {

  protected static WebDriver driver;

  @BeforeClass
  public static void setUp() {
    System.setProperty(Constants.SetUp.CHROME_WEBDRIVER, Constants.SetUp.CHROME_WEBDRIVER_PATH);
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
  }

  @After
  public void cleanUp() {
    driver.manage().deleteAllCookies();
  }

  @AfterClass
  public static void tearDown() {
    driver.close();
  }
}

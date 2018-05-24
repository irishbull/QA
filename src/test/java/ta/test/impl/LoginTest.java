package ta.test.impl;

import org.openqa.selenium.NoSuchElementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import ta.driver.SeleniumDriver;
import ta.pageobjects.impl.LoginPO;
import ta.pageobjects.impl.WelcomePO;
import ta.test.BaseTest;

import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {

  private static final Logger logger = LoggerFactory.getLogger(LoginTest.class);

  @Test
  public void loginSuccess() throws Exception {

    SeleniumDriver.getInstance().getDriver().get("http://opensource.demo.orangehrmlive.com/");

    LoginPO loginPage = new LoginPO();
    loginPage.enterUsernameAndPassword("Admin", "admin");

    WelcomePO welcomePO = loginPage.submit();

    logger.info("Title = " + SeleniumDriver.getInstance().getDriver().getTitle());

    assertTrue(SeleniumDriver.getInstance().getDriver().getTitle().contains("OrangeHRM"));

    assertTrue(welcomePO.getDashboardElem().contains("Dashboard"));
  }


  @Test
  public void loginFailure() throws Exception {

    try {
      SeleniumDriver.getInstance().getDriver().get("http://opensource.demo.orangehrmlive.com/");

      LoginPO loginPage = new LoginPO();
      loginPage.enterUsernameAndPassword("username", "password");

      WelcomePO welcomePO = loginPage.submit();

      logger.info("Title = " + SeleniumDriver.getInstance().getDriver().getTitle());

      assertTrue(SeleniumDriver.getInstance().getDriver().getTitle().contains("OrangeHRM"));

      assertTrue(welcomePO.getDashboardElem().contains("Dashboard"));
    } catch (NoSuchElementException nsee) {
      logger.error(nsee.getMessage());
      Assert.fail(nsee.toString());
    }
  }

}


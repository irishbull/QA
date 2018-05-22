package ta.test.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import ta.driver.SeleniumDriver;
import ta.pageobjects.impl.LoginPO;
import ta.pageobjects.impl.WelcomePO;
import ta.test.BaseTest;

import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {

  private static final Logger logger = LoggerFactory.getLogger(LoginTest.class);

  @Test
  public void testLoginOK() throws Exception {

    SeleniumDriver.getInstance().getDriver().get("http://opensource.demo.orangehrmlive.com/");

    LoginPO loginPage = new LoginPO();
    loginPage.enterUsernameAndPassword("admin", "admin");

    WelcomePO welcomePO = loginPage.submit();

    logger.info("Title = " + SeleniumDriver.getInstance().getDriver().getTitle());

    assertTrue(SeleniumDriver.getInstance().getDriver().getTitle().contains("OrangeHRM"));

    assertTrue(welcomePO.getDashboardElem().contains("Dashboard"));
  }
}


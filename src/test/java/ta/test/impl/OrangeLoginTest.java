package ta.test.impl;

import org.openqa.selenium.NoSuchElementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import ta.driver.SeleniumDriver;
import ta.pageobjects.impl.OrangeLoginPO;
import ta.pageobjects.impl.OrangeWelcomePO;
import ta.test.BaseTest;

import static org.testng.Assert.assertTrue;

public class OrangeLoginTest extends BaseTest {

  private static final Logger logger = LoggerFactory.getLogger(OrangeLoginTest.class);

  @Test
  public void loginSuccess() throws Exception {

    SeleniumDriver.getInstance().getDriver().get("http://opensource.demo.orangehrmlive.com/");

    OrangeLoginPO loginPage = new OrangeLoginPO();
    loginPage.enterUsernameAndPassword("Admin", "admin");

    OrangeWelcomePO orangeWelcomePO = loginPage.submit();

    logger.info("Title = " + SeleniumDriver.getInstance().getDriver().getTitle());

    assertTrue(SeleniumDriver.getInstance().getDriver().getTitle().contains("OrangeHRM"));

    assertTrue(orangeWelcomePO.getDashboardElem().contains("Dashboard"));
  }


  @Test
  public void loginFailure() throws Exception {

    try {
      SeleniumDriver.getInstance().getDriver().get("http://opensource.demo.orangehrmlive.com/");

      OrangeLoginPO loginPage = new OrangeLoginPO();
      loginPage.enterUsernameAndPassword("username", "password");

      OrangeWelcomePO orangeWelcomePO = loginPage.submit();

      logger.info("Title = " + SeleniumDriver.getInstance().getDriver().getTitle());

      assertTrue(SeleniumDriver.getInstance().getDriver().getTitle().contains("OrangeHRM"));

      assertTrue(orangeWelcomePO.getDashboardElem().contains("Dashboard"));
    } catch (NoSuchElementException nsee) {
      logger.error(nsee.getMessage());
      Assert.fail(nsee.toString());
    }
  }

}


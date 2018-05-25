package ta.test.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import ta.driver.SeleniumDriver;
import ta.pageobjects.impl.OrangeLoginPO;
import ta.pageobjects.impl.OrangeWelcomePO;
import ta.test.BaseTest;
import ta.utilities.BrowserUtils;

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

    assertTrue(BrowserUtils.exists(orangeWelcomePO.getDashboardElem(), 10));
  }


  @Test
  public void loginFailure() throws Exception {

    SeleniumDriver.getInstance().getDriver().get("http://opensource.demo.orangehrmlive.com/");

    OrangeLoginPO loginPage = new OrangeLoginPO();
    loginPage.enterUsernameAndPassword("username", "password");

    OrangeWelcomePO orangeWelcomePO = loginPage.submit();

    assertTrue(!BrowserUtils.exists(orangeWelcomePO.getDashboardElem(), 10));
  }

}


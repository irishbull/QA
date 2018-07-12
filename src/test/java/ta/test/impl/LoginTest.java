package ta.test.impl;

import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import ta.driver.SeleniumDriver;
import ta.pageobjects.impl.HomePagePO;
import ta.pageobjects.impl.LoginPO;
import ta.pageobjects.impl.OrangeLoginPO;
import ta.pageobjects.impl.OrangeWelcomePO;
import ta.test.BaseTest;
import ta.utilities.BrowserUtils;
import ta.utilities.Constants;
import ta.utilities.ReadPropertiesFile;

import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(LoginTest.class);

    @Test
    @Description("Login test with valid username and password")
    public void loginSuccess() throws Exception {

        SeleniumDriver.getInstance().getDriver().get(ReadPropertiesFile.getProperty("qa.base.url"));

        HomePagePO homePagePO = new HomePagePO();

        LoginPO loginPO = homePagePO.clickLoginIconLink();

        loginPO.enterUsernameAndPassword("giovanni.bologna@nttdata.com", "lymn1234");

        homePagePO = loginPO.clickLoginButton();

        BrowserUtils.exists(homePagePO.getFirstNameDiv(),Constants.WaitTime.EXPLICIT_WAIT);

        logger.info("First name = {}", homePagePO.getLoginIcon().findElement(By.tagName("div")).getText());

        assertTrue(homePagePO.getFirstNameDiv().getText().equalsIgnoreCase("giovanni"));

    }
}


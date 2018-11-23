package ta.test.impl;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import ta.dataproviders.JSONDataProvider;
import ta.driver.SeleniumDriver;
import ta.pageobjects.impl.HomePagePO;
import ta.pageobjects.impl.LoginPO;
import ta.test.BaseTest;
import ta.utilities.BrowserUtils;
import ta.utilities.constants.Constants;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static ta.utilities.constants.Constants.Url.BASE_URL;

public class LoginTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(LoginTest.class);


    @BeforeMethod
    public void beforeTest() {

        SeleniumDriver.getInstance().getDriver().get(BASE_URL);

        HomePagePO homePagePO = new HomePagePO();

        if (BrowserUtils.exists(homePagePO.getAcceptCookiesButton(), Constants.WaitTime.EXPLICIT_WAIT)) {
            homePagePO.getAcceptCookiesButton().click();
            logger.debug("Cookies accepted");
        }
    }


    @Test(dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class)
    @Description("Login test with valid username and password")
    public void tc_001_loginSuccess(JSONObject testData) {

        HomePagePO homePagePO = new HomePagePO();

        LoginPO loginPO = homePagePO.clickLoginIconLink();

        loginPO.enterUsernameAndPassword(testData.get("username").toString(), testData.get("password").toString());

        homePagePO = loginPO.clickLoginButton();

        // after successful login user is redirected to homepage
        assertEquals(SeleniumDriver.getInstance().getDriver().getCurrentUrl(), BASE_URL, "Page url");

        BrowserUtils.waitFor(homePagePO.getUserFirstName(), Constants.WaitTime.EXPLICIT_WAIT);
        // after successful user firstName has the expected value
        assertEquals(homePagePO.getUserFirstName().getText(), testData.get("firstName").toString(), "User first name");
    }


    @Test(dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class)
    @Description("Login test with erroneous username or password")
    public void tc_002_loginFailure(JSONObject testData) {

        HomePagePO homePagePO = new HomePagePO();

        LoginPO loginPO = homePagePO.clickLoginIconLink();

        loginPO.enterUsernameAndPassword(testData.get("username").toString(), testData.get("password").toString());

        loginPO.clickLoginButton();

        // error message wrapper exists
        assertTrue(BrowserUtils.exists(loginPO.getLoginErrorMessageWrapper(), Constants.WaitTime.EXPLICIT_WAIT));

        // error message has the expected value
        assertEquals(loginPO.getLoginErrorMessageWrapper().getText(), testData.get("expectedValue").toString(), "Error message");
    }
}


package ta.test.impl.registration;

import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
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
import static ta.utilities.constants.Constants.Url.BASE_URL;

public class Login extends BaseTest {

    @Test(dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class)
    @Description("Login test with valid username and password")

    public void tc_001_loginSuccess(JSONObject testData) {

        WebDriver driver = SeleniumDriver.getInstance().getDriver();
        driver.get(BASE_URL);
        HomePagePO homePagePO = new HomePagePO();
        LoginPO loginPO = homePagePO.clickLoginIconLink();
        loginPO.enterUsernameAndPassword(testData.get("username").toString(), testData.get("password").toString());
        loginPO.clickcookie();
        loginPO.clickAccedi();
        BrowserUtils.waitFor(homePagePO.getUserFromHomePage(), Constants.WaitTime.EXPLICIT_WAIT);
        assertEquals(homePagePO.getCustomerName().getText(), testData.get("firstName").toString(), "User first name");
        loginPO.logout();
    }

    @Test(dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class)
    @Description("Login test with NOT valid username and password")
    public void tc_002_loginFailure(JSONObject testData) {
        WebDriver driver = SeleniumDriver.getInstance().getDriver();
        driver.get(BASE_URL);
        HomePagePO homePagePO = new HomePagePO();
        LoginPO loginPO = homePagePO.clickLoginIconLink();
        loginPO.enterUsernameAndPassword(testData.get("username").toString(), testData.get("password").toString());
        loginPO.clickAccedi();
        BrowserUtils.waitFor(homePagePO.getUserFromHomePage(), Constants.WaitTime.EXPLICIT_WAIT);
        // after successful user firstName has the expected value
        assertEquals(homePagePO.getUserFromHomePage().getText(), testData.get("firstName").toString(), "User first name");
    }
}

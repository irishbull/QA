package ta.test.impl.contextchange;

import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import ta.dataproviders.JSONDataProvider;
import ta.driver.SeleniumDriver;
import ta.pageobjects.impl.AngularLoginPO;
import ta.pageobjects.impl.HomePagePO;
import ta.test.BaseTest;
import ta.utilities.BrowserUtils;
import ta.utilities.constants.Constants;

import static org.testng.Assert.assertEquals;
import static ta.utilities.constants.Constants.Url.BASE_URL;

public class ContextChangeARATest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(ContextChangeARATest.class);


    @Test(priority = 1, dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class)
    @Description("Add product to cart")
    public void tc_001_angularLogin(JSONObject testData) {

        WebDriver driver = SeleniumDriver.getInstance().getDriver();

        String url = BASE_URL + testData.get("pathAndQuery").toString();

        driver.get(url);

        AngularLoginPO loginPO = new AngularLoginPO();

        loginPO.enterUsernameAndPassword(testData.get("username").toString(), testData.get("password").toString());

        HomePagePO homePagePO = loginPO.clickLoginButton();

        // after successful login user is redirected to homepage
        assertEquals(SeleniumDriver.getInstance().getDriver().getCurrentUrl(), url, "Page url");

        BrowserUtils.waitFor(homePagePO.getUserFirstName(), Constants.WaitTime.EXPLICIT_WAIT);

        // after successful user firstName has the expected value
        assertEquals(homePagePO.getUserFirstName().getText(), testData.get("firstName").toString(), "User first name");

    }
}

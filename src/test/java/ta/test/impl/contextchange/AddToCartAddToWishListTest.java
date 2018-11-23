package ta.test.impl.contextchange;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import ta.dataproviders.JSONDataProvider;
import ta.driver.SeleniumDriver;
import ta.pageobjects.impl.AngularCartItemsListPO;
import ta.pageobjects.impl.AngularHeaderCommonPO;
import ta.pageobjects.impl.HomePagePO;
import ta.pageobjects.impl.LoginPO;
import ta.test.BaseTest;
import ta.utilities.BrowserUtils;

import static org.testng.Assert.assertEquals;
import static ta.utilities.constants.Constants.Url.BASE_URL;
import static ta.utilities.constants.Constants.WaitTime.FIFTEEN_SECONDS;
import static ta.utilities.constants.Constants.WaitTime.TEN_SECONDS;

public class AddToCartAddToWishListTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(AddToCartAddToWishListTest.class);


    @Test(priority = 1, dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class)
    @Description("Login from angular context")
    public void tc_001_loginFromAngularContext(JSONObject testData) {

        // login from react context
        WebDriver driver = SeleniumDriver.getInstance().getDriver();
        driver.get(BASE_URL);
        HomePagePO homePagePO = new HomePagePO();

        LoginPO loginPO = homePagePO.clickLoginIconLink();
        loginPO.enterUsernameAndPassword(testData.get("username").toString(), testData.get("password").toString());
        loginPO.clickAccedi();

        // wait page redirect - find element that appears only in mylm when user is logged
        BrowserUtils.waitForURLContains("/mylm", TEN_SECONDS );
        BrowserUtils.waitFor(driver.findElement(By.name("mylm_logout")), FIFTEEN_SECONDS);
        AngularHeaderCommonPO angularHeaderCommonPO = new AngularHeaderCommonPO();

        // assert that user first name is correct
        BrowserUtils.waitForTextMatches(angularHeaderCommonPO.getCustomerName(), TEN_SECONDS, testData.get("userFirstName").toString());
        assertEquals(angularHeaderCommonPO.getCustomerName().getText(), testData.get("userFirstName").toString(), "Angular context. User first name");
    }


    @Test(priority = 2)
    @Description("Empty cart")
    public void tc_002_emptyUserCart() {

        WebDriver driver = SeleniumDriver.getInstance().getDriver();
        driver.get(BASE_URL + "/checkout/carrello");

        AngularCartItemsListPO angularCartItemsListPO = new AngularCartItemsListPO();
        logger.info("Number of entries = " + angularCartItemsListPO.getListSize());
        // TODO remove entries
    }
}

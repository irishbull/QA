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
import ta.pageobjects.impl.AngularHeaderCommonPO;
import ta.pageobjects.impl.AngularLoginPO;
import ta.pageobjects.impl.AngularMyLmPO;
import ta.pageobjects.impl.HeaderPO;
import ta.pageobjects.impl.HomePagePO;
import ta.pageobjects.impl.LoginPO;
import ta.test.BaseTest;
import ta.utilities.BrowserUtils;

import static org.testng.Assert.assertEquals;
import static ta.utilities.constants.Constants.Url.BASE_URL;
import static ta.utilities.constants.Constants.WaitTime.EXPLICIT_WAIT;
import static ta.utilities.constants.Constants.WaitTime.FIFTEEN_SECONDS;
import static ta.utilities.constants.Constants.WaitTime.TEN_SECONDS;

public class ContextChangeARATest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(ContextChangeARATest.class);

    @Test(priority = 1, dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class)
    @Description("<ul><li>effettuo la login da pagina in contesto angular [<code>angularPage1</code>]</li>" +
            "<li>navigo su pagina in contesto react [homepage] e verifico che l'utente sia ancora loggato</li>" +
            "<li>ritorno su pagina in contesto angular [<code>angularPage2</code>] e verifico che l'utente sia ancora loggato</li>" +
            "<li>effettuo il logout</li>")
    public void tc_001_angularReactAngular(JSONObject testData) {

        WebDriver driver = SeleniumDriver.getInstance().getDriver();

        // navigate to page 1 - angular context
        driver.get(BASE_URL + testData.get("angularPage1").toString());

        // login from angular context
        AngularLoginPO loginPO = new AngularLoginPO();
        loginPO.enterUsernameAndPassword(testData.get("username").toString(), testData.get("password").toString());

        loginPO.clickLoginButton();

        // wait page redirect - find element that appears only when user is logged from angular
        BrowserUtils.waitFor(driver.findElement(By.className("form-section-label")), FIFTEEN_SECONDS);
        AngularHeaderCommonPO angularHeaderCommonPO = new AngularHeaderCommonPO();

        // assert that user first name is correct
        BrowserUtils.waitForTextMatches(angularHeaderCommonPO.getCustomerName(), TEN_SECONDS, testData.get("userFirstName").toString());
        assertEquals(angularHeaderCommonPO.getCustomerName().getText(), testData.get("userFirstName").toString(), "Angular context. User first name");


        // navigate to homepage - react context
        angularHeaderCommonPO.clickLinkToHome();

        // assert that user first name is correct - react context
        HeaderPO headerPO = new HeaderPO();
        BrowserUtils.waitFor(headerPO.getHeaderLoginElem(), EXPLICIT_WAIT);
        BrowserUtils.waitForTextMatches(headerPO.getCustomerNameElem(), TEN_SECONDS, testData.get("userFirstName").toString());
        assertEquals(headerPO.getCustomerNameElem().getText(), testData.get("userFirstName").toString(), "React context. User first name");

        // navigate to page 2 - angular context
        driver.get(BASE_URL + testData.get("angularPage2").toString());
        angularHeaderCommonPO = new AngularHeaderCommonPO();

        // login user firstName has the expected value
        BrowserUtils.waitFor(angularHeaderCommonPO.getCustomerName(), EXPLICIT_WAIT);
        BrowserUtils.waitForTextMatches(angularHeaderCommonPO.getCustomerName(), TEN_SECONDS, testData.get("userFirstName").toString());
        assertEquals(angularHeaderCommonPO.getCustomerName().getText(), testData.get("userFirstName").toString(), "Angular context. User first name");

        // logout
        AngularMyLmPO myLmPO = new AngularMyLmPO();
        myLmPO.logout();

    }


    @Test(priority = 2, dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class)
    @Description("<ul><li>effettuo la login da pagina in contesto react [<code>reactPage1</code>]</li>" +
            "<li>navigo su pagina in contesto angular [<code>/mylm</code>] e verifico che l'utente sia ancora loggato</li>" +
            "<li>ritorno su pagina in contesto react [<code>reactPage2</code>] e verifico che l'utente sia ancora loggato</li>" +
            "<li>effettuo il logout</li>")
    public void tc_002_reactAngularReact(JSONObject testData) {

        // login from react context
        WebDriver driver = SeleniumDriver.getInstance().getDriver();
        driver.get(BASE_URL);
        HomePagePO homePagePO = new HomePagePO();

        LoginPO loginPO = homePagePO.clickLoginIconLink();
        loginPO.enterUsernameAndPassword(testData.get("username").toString(), testData.get("password").toString());
        loginPO.clickAccedi();

        // wait page redirect - find element that appears only in mylm when user is logged
        BrowserUtils.waitFor(driver.findElement(By.className("tiles-content")), FIFTEEN_SECONDS);
        AngularHeaderCommonPO angularHeaderCommonPO = new AngularHeaderCommonPO();

        // assert that user first name is correct
        BrowserUtils.waitForTextMatches(angularHeaderCommonPO.getCustomerName(), TEN_SECONDS, testData.get("userFirstName").toString());
        assertEquals(angularHeaderCommonPO.getCustomerName().getText(), testData.get("userFirstName").toString(), "Angular context. User first name");


        // navigate to homepage - react context
        angularHeaderCommonPO.clickLinkToHome();

        // assert that user first name is correct - react context
        HeaderPO headerPO = new HeaderPO();
        BrowserUtils.waitFor(headerPO.getHeaderLoginElem(), EXPLICIT_WAIT);
        BrowserUtils.waitForTextMatches(headerPO.getCustomerNameElem(), TEN_SECONDS, testData.get("userFirstName").toString());
        assertEquals(headerPO.getCustomerNameElem().getText(), testData.get("userFirstName").toString(), "React context. User first name");

        // navigate to page 2 - angular context
        driver.get(BASE_URL + testData.get("angularPage2").toString());
        angularHeaderCommonPO = new AngularHeaderCommonPO();

        // login user firstName has the expected value
        BrowserUtils.waitFor(angularHeaderCommonPO.getCustomerName(), EXPLICIT_WAIT);
        BrowserUtils.waitForTextMatches(angularHeaderCommonPO.getCustomerName(), TEN_SECONDS, testData.get("userFirstName").toString());
        assertEquals(angularHeaderCommonPO.getCustomerName().getText(), testData.get("userFirstName").toString(), "Angular context. User first name");

        // logout
        AngularMyLmPO myLmPO = new AngularMyLmPO();
        myLmPO.logout();

    }

}

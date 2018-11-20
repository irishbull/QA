package ta.test.impl.contextchange;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import ta.dataproviders.JSONDataProvider;
import ta.driver.SeleniumDriver;
import ta.pageobjects.impl.AngularHeaderCommonPO;
import ta.pageobjects.impl.AngularLoginPO;
import ta.pageobjects.impl.HeaderPO;
import ta.pageobjects.impl.HomePagePO;
import ta.pageobjects.impl.LoginPO;
import ta.test.BaseTest;
import ta.utilities.BrowserUtils;
import ta.utilities.LocalStorage;
import ta.utilities.constants.Constants;

import static org.testng.Assert.assertEquals;
import static ta.utilities.constants.Constants.Url.BASE_URL;
import static ta.utilities.constants.Constants.WaitTime.EXPLICIT_WAIT;
import static ta.utilities.constants.Constants.WaitTime.TEN_SECONDS;

public class ContextChangeARATest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(ContextChangeARATest.class);



    @AfterMethod
    public void clear() {
        SeleniumDriver.getInstance().getDriver().manage().deleteAllCookies();
        LocalStorage.clear();
    }


    @Test(priority = 1, dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class)
    @Description("<ul><li>effettuo la login da pagina in contesto angular [<code>angularPage1</code>]</li>" +
            "<li>navigo su pagina in contesto react [homepage] e verifico che l'utente sia ancora loggato</li>" +
            "<li>ritorno su pagina in contesto angular [<code>angularPage2</code>] e verifico che l'utente sia ancora loggato</li>")
    public void tc_001_angularReactAngular(JSONObject testData) {

        WebDriver driver = SeleniumDriver.getInstance().getDriver();

        // navigate to page 1 - angular context
        driver.get(BASE_URL + testData.get("angularPage1").toString());

        // login - angular context
        AngularLoginPO loginPO = new AngularLoginPO();
        loginPO.enterUsernameAndPassword(testData.get("username").toString(), testData.get("password").toString());

        loginPO.clickLoginButton();

        // wait page reload - find element that appears only when user is logged
        BrowserUtils.waitFor(driver.findElement(By.className("form-section-label")), 15);
        AngularHeaderCommonPO headerCommonPO = new AngularHeaderCommonPO();

        // assert that user first name is correct
        assertEquals(headerCommonPO.getCustomerName().getText(), testData.get("userFirstName").toString(), "Angular context. User first name");


        // navigate to homepage - react context
        headerCommonPO.clickLinkToHome();

        // assert that user first name is correct - react context
        HeaderPO headerPO = new HeaderPO();
        BrowserUtils.waitFor(headerPO.getHeaderLoginElem(), EXPLICIT_WAIT);
        BrowserUtils.waitForTextMatches(headerPO.getCustomerNameElem(), TEN_SECONDS, testData.get("userFirstName").toString());
        assertEquals(headerPO.getCustomerNameElem().getText(), testData.get("userFirstName").toString(), "React context. User first name");

        // navigate to page 2 - angular context
        driver.get(BASE_URL + testData.get("angularPage2").toString());
        headerCommonPO = new AngularHeaderCommonPO();

        // login user firstName has the expected value
        BrowserUtils.waitFor(headerCommonPO.getCustomerName(), EXPLICIT_WAIT);
        BrowserUtils.waitForTextMatches(headerCommonPO.getCustomerName(), TEN_SECONDS, testData.get("userFirstName").toString());
        assertEquals(headerCommonPO.getCustomerName().getText(), testData.get("userFirstName").toString(), "Angular context. User first name");
    }

    /*@Test(priority = 2, dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class)
    @Description("<ul><li>effettuo la login da pagina in contesto react [<code>reactPage1</code>]</li>" +
            "<li>navigo su pagina in contesto angular [<code>angularPage1</code>] e verifico che l'utente sia ancora loggato</li>" +
            "<li>ritorno su pagina in contesto react [<code>reactPage2</code>] e verifico che l'utente sia ancora loggato</li>")
    public void tc_002_reactAngularReact(JSONObject testData) {

       *//* WebDriver driver = SeleniumDriver.getInstance().getDriver();

        driver.get(BASE_URL + testData.get("reactPage1").toString()+ BASE_URL);
        *//*

        HomePagePO homePagePO = new HomePagePO();

        LoginPO loginPO = homePagePO.clickLoginIconLink();

        loginPO.enterUsernameAndPassword(testData.get("username").toString(), testData.get("password").toString());

        loginPO.clickAccedi();

        homePagePO = new HomePagePO();

        BrowserUtils.waitFor(homePagePO.getUserFirstName(), Constants.WaitTime.EXPLICIT_WAIT);
        BrowserUtils.waitForTextMatches(homePagePO.getUserFirstName(), TEN_SECONDS, testData.get("userFirstName").toString());
        // after successful user firstName has the expected value
        assertEquals(homePagePO.getUserFirstName().getText(), testData.get("userFirstName").toString(), "User first name");

    }*/



}

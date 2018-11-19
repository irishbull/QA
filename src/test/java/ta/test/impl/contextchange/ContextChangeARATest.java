package ta.test.impl.contextchange;

import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import ta.dataproviders.JSONDataProvider;
import ta.driver.SeleniumDriver;
import ta.pageobjects.impl.AngularHeaderCommonPO;
import ta.pageobjects.impl.AngularLoginPO;
import ta.pageobjects.impl.HeaderPO;
import ta.test.BaseTest;
import ta.utilities.BrowserUtils;

import static org.testng.Assert.assertEquals;
import static ta.utilities.constants.Constants.Url.BASE_URL;
import static ta.utilities.constants.Constants.WaitTime.EXPLICIT_WAIT;
import static ta.utilities.constants.Constants.WaitTime.FIVE_SEC;

public class ContextChangeARATest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(ContextChangeARATest.class);


    @Test(priority = 1, dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class)
    @Description("<ul><li>effettuo la login da pagina in contesto angular [<code>angularPage1</code>]</li>" +
            "<li>navigo su pagina in contesto react [homepage] e verifico che l'utente sia ancora loggato</li>" +
            "<li>ritorno su pagina in contesto angular [<code>angularPage2</code>] e verifico che l'utente sia ancora loggato</li>")
    public void tc_001_angularReactAngular(JSONObject testData) throws InterruptedException {

        WebDriver driver = SeleniumDriver.getInstance().getDriver();

        // navigate to page 1 - angular context
        driver.get(BASE_URL + testData.get("angularPage1").toString());

        // login - angular context
        AngularLoginPO loginPO = new AngularLoginPO();
        loginPO.enterUsernameAndPassword(testData.get("username").toString(), testData.get("password").toString());
        AngularHeaderCommonPO headerCommonPO = loginPO.clickLoginButton();

        // after successful login user first name has the expected value
        BrowserUtils.waitFor(headerCommonPO.getCustomerName(), EXPLICIT_WAIT);
        BrowserUtils.waitForTextMatches(headerCommonPO.getCustomerName(), FIVE_SEC, testData.get("userFirstName").toString());

        // avoid element stale
        AngularHeaderCommonPO angularHeaderCommonPO = new AngularHeaderCommonPO();
        assertEquals(angularHeaderCommonPO.getCustomerName().getText(), testData.get("userFirstName").toString(), "Angular context. User first name");


        // navigate to homepage - react context
        angularHeaderCommonPO.getLogoContainer().click();

        // user first name has the expected value
        HeaderPO headerPO = new HeaderPO();
        BrowserUtils.waitFor(headerPO.getHeaderLoginElem(), EXPLICIT_WAIT);
        logger.info(" contains " + BrowserUtils.elementContainsText(headerPO.getCustomerNameElem(), EXPLICIT_WAIT, testData.get("userFirstName").toString()));
        assertEquals(headerPO.getCustomerNameElem().getText(), testData.get("userFirstName").toString(), "React context. User first name");


        // navigate to angular page
        driver.get(BASE_URL + testData.get("angularPage2").toString());
        headerCommonPO = new AngularHeaderCommonPO();

        // login user firstName has the expected value
        BrowserUtils.waitFor(headerCommonPO.getCustomerName(), EXPLICIT_WAIT);
        BrowserUtils.elementContainsText(headerCommonPO.getCustomerName(), EXPLICIT_WAIT, testData.get("userFirstName").toString());
        assertEquals(headerCommonPO.getCustomerName().getText(), testData.get("userFirstName").toString(), "Angular context. User first name");

    }
}

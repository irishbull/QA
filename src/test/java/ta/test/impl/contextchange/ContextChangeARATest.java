package ta.test.impl.contextchange;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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


    @Test(priority = 1, dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class)
    @Description("<div>Given: utente non loggato&nbsp;</div>\n" +
            "<div>When: utente effettua la login da pagina in contesto angular (<span style=\"font-family:courier new,courier,monospace\">https://www.leroymerlin.it/adesione/datiContatto</span>)</div>\n" +
            "<div>&nbsp; &nbsp; &nbsp; And:&nbsp;utente naviga su pagina in contesto react&nbsp;(<span style=\"font-family:courier new,courier,monospace\">https://www.leroymerlin.it</span>)&nbsp;</div>\n" +
            "<div>&nbsp; &nbsp; &nbsp; And:&nbsp;utente naviga su pagina in contesto angular&nbsp;(<span style=\"font-family:courier new,courier,monospace\">https://www.leroymerlin.it/mylm</span>)&nbsp;</div>\n" +
            "<div>Then: utente resta loggato&nbsp;</div>")
    public void tc_001_angularReactAngular(JSONObject testData) {

        // navigate to page 1 - angular context
        WebDriver driver = SeleniumDriver.getInstance().getDriver();
        driver.get(BASE_URL + "/adesione/datiContatto");

        // login from angular context
        AngularLoginPO loginPO = new AngularLoginPO();
        loginPO.enterUsernameAndPassword(testData.get("username").toString(), testData.get("password").toString());
        loginPO.clickLoginButton();

        // wait page redirect - find element that appears only when user is logged from angular
        BrowserUtils.waitFor(driver.findElement(By.className("form-section-label")), 60);
        AngularHeaderCommonPO angularHeaderCommonPO = new AngularHeaderCommonPO();

        // assert that user first name has the expected value - angular context
        BrowserUtils.waitForTextMatches(angularHeaderCommonPO.getCustomerName(), 60, testData.get("userFirstName").toString());
        assertEquals(angularHeaderCommonPO.getCustomerName().getText(), testData.get("userFirstName").toString(), "Angular context. User first name");


        // navigate to homepage - react context
        angularHeaderCommonPO.clickLinkToHome();
        BrowserUtils.waitForURLMatches(BASE_URL, 60);

        // assert that user first name has the expected value - react context
        HeaderPO headerPO = new HeaderPO();
        BrowserUtils.waitFor(headerPO.getHeaderLoginElem(), 60);
        BrowserUtils.waitForTextMatches(headerPO.getCustomerNameElem(), 60, testData.get("userFirstName").toString());
        assertEquals(headerPO.getCustomerNameElem().getText(), testData.get("userFirstName").toString(), "React context. User first name");


        // navigate to /mylm - angular context
        driver.get(BASE_URL + "/mylm");
        BrowserUtils.waitForURLContains("mylm", 60);

        angularHeaderCommonPO = new AngularHeaderCommonPO();

        // assert that user first name has the expected value - angular context
        BrowserUtils.waitFor(angularHeaderCommonPO.getCustomerName(), 60);
        BrowserUtils.waitForTextMatches(angularHeaderCommonPO.getCustomerName(), 60, testData.get("userFirstName").toString());
        assertEquals(angularHeaderCommonPO.getCustomerName().getText(), testData.get("userFirstName").toString(), "Angular context. User first name");

        // logout
        AngularMyLmPO myLmPO = new AngularMyLmPO();
        myLmPO.logout();

    }



    @Test(priority = 2, dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class)
    @Description("<div>Given: utente non loggato&nbsp;</div>\n" +
            "<div>When: utente effuttua la login da pagina in contesto react (<span style=\"font-family:courier new,courier,monospace\">https://www.leroymerlin.it/login?redirectUrl=https://www.leroymerlin.it</span>)</div>\n" +
            "<div>&nbsp; &nbsp; &nbsp; And: utente naviga su pagina in contesto angular (<span style=\"font-family:courier new,courier,monospace\">https://www.leroymerlin.it/mylm</span>)&nbsp;</div>\n" +
            "<div>&nbsp; &nbsp; &nbsp; And: utente naviga su pagina in contesto react&nbsp;(<span style=\"font-family:courier new,courier,monospace\">https://www.leroymerlin.it</span>)</div>\n" +
            "<div>Then: utente resta loggato&nbsp;&nbsp;</div>")
    public void tc_002_reactAngularReact(JSONObject testData) {

        // login from react context
        WebDriver driver = SeleniumDriver.getInstance().getDriver();
        driver.get(BASE_URL);
        HomePagePO homePagePO = new HomePagePO();
        LoginPO loginPO = homePagePO.clickLoginIconLink();
        loginPO.enterUsernameAndPassword(testData.get("username").toString(), testData.get("password").toString());
        loginPO.clickAccedi();

        // wait page redirect - find element that appears only in /mylm when user is logged - angular context
        BrowserUtils.waitForURLContains("/mylm", 60);
        BrowserUtils.waitFor(driver.findElement(By.name("mylm_logout")), 60);
        AngularHeaderCommonPO angularHeaderCommonPO = new AngularHeaderCommonPO();

        // assert that user first name has the expected value - angular context
        BrowserUtils.waitForTextMatches(angularHeaderCommonPO.getCustomerName(), 60, testData.get("userFirstName").toString());
        assertEquals(angularHeaderCommonPO.getCustomerName().getText(), testData.get("userFirstName").toString(), "Angular context. User first name");


        // navigate to homepage - react context
        angularHeaderCommonPO.clickLinkToHome();
        BrowserUtils.waitForURLMatches(BASE_URL, 60);

        // assert that user first name has the expected value - react context
        HeaderPO headerPO = new HeaderPO();
        BrowserUtils.waitFor(headerPO.getHeaderLoginElem(), 60);
        BrowserUtils.waitForTextMatches(headerPO.getCustomerNameElem(), 60, testData.get("userFirstName").toString());
        assertEquals(headerPO.getCustomerNameElem().getText(), testData.get("userFirstName").toString(), "React context. User first name");


        // navigate to /mylm angular context
        driver.get(BASE_URL + "/mylm");
        BrowserUtils.waitForURLContains("mylm", 60);
        angularHeaderCommonPO = new AngularHeaderCommonPO();

        // assert that user first name has the expected value - angular context
        BrowserUtils.waitFor(angularHeaderCommonPO.getCustomerName(), 60);
        BrowserUtils.waitForTextMatches(angularHeaderCommonPO.getCustomerName(), 60, testData.get("userFirstName").toString());
        assertEquals(angularHeaderCommonPO.getCustomerName().getText(), testData.get("userFirstName").toString(), "Angular context. User first name");

        // logout
        AngularMyLmPO myLmPO = new AngularMyLmPO();
        myLmPO.logout();

    }

}

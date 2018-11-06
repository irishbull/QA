package ta.test.impl.registration;

import com.sun.jmx.snmp.Timestamp;
import io.qameta.allure.Description;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import ta.dataproviders.JSONDataProvider;
import ta.driver.SeleniumDriver;
import ta.pageobjects.impl.HomePagePO;
import ta.pageobjects.impl.LoginPO;
import ta.pageobjects.impl.RegisterPO;
import ta.test.BaseTest;

import static org.testng.Assert.assertEquals;
import static ta.utilities.constants.Constants.Url.BASE_URL;

public class NewAccount extends BaseTest {

    @Test(dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class, priority = 0)
    @Description("Create New Account Private ")
    public void tc_001_newAccount(JSONObject testData) throws Exception {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        WebDriver driver = SeleniumDriver.getInstance().getDriver();
        RegisterPO register = new RegisterPO();
        LoginPO user = new LoginPO();
        HomePagePO gologin = new HomePagePO();
        gologin.clickLogin();
        register.registratiClick();
        register.selPrivatoClick();
        register.genderClick();
        register.maleClick();
        register.enterUsernameAndPassword(testData.get("nome").toString(), testData.get("cognome").toString(),
            timestamp.getDateTime() + testData.get("email").toString() + ".it", testData.get("password").toString(), testData.get("numeroditelefono").toString(), testData.get("cap").toString());
        register.selectStore();
        register.continueClick();
        register.clickrifiuto();
        register.accettaTermini();
        register.profilo();
        register.concludiRegistrazione();
        register.goProfileButton();
        new WebDriverWait(driver, 20).until(ExpectedConditions.urlContains("mylm"));
         assertEquals(SeleniumDriver.getInstance().getDriver().getCurrentUrl(), BASE_URL + testData.get("landingurl"), "Page url");
         user.logout();
    }

    @Test(dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class , priority = 1)
    @Description("Create New Account Azienda")

    public void tc_002_newAccount(JSONObject testData) throws Exception {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        WebDriver driver = SeleniumDriver.getInstance().getDriver();
        RegisterPO register = new RegisterPO();
        HomePagePO gologin = new HomePagePO();
        gologin.clickLogin();
        register.registratiClick();
        register.aziendaClick();
        register.genderClick();
        register.maleClick();
        register.settoreClick();
        Thread.sleep(5000);
        register.enterFormCompany(testData.get("ragioneSociale").toString(), testData.get("nome").toString(), testData.get("cognome").toString(),
            timestamp.getDateTime() + testData.get("email").toString() + ".it", testData.get("password").toString(), testData.get("numeroditelefono").toString(), testData.get("cap").toString());
        register.selectStore();
        Thread.sleep(6000);
        register.concludiRegistrazione();

    }
    @Test(dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class, priority = 2)
    @Description("Create New Account Private Failed ")
    public void tc_003_newAccount(JSONObject testData) throws Exception {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        WebDriver driver = SeleniumDriver.getInstance().getDriver();
        RegisterPO register = new RegisterPO();
        HomePagePO gologin = new HomePagePO();
        gologin.clickLogin();
        register.registratiClick();
        register.aziendaClick();
        register.genderClick();
        register.maleClick();
        register.settoreClick();
        Thread.sleep(5000);
        register.enterFormCompany(testData.get("ragioneSociale").toString(), testData.get("nome").toString(), testData.get("cognome").toString(),
            timestamp.getDateTime() + testData.get("email").toString() + ".it", testData.get("password").toString(), testData.get("numeroditelefono").toString(), testData.get("cap").toString());
        register.selectStore();
        Thread.sleep(6000);
        register.concludiRegistrazione();

    }
    @Test(dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class, priority = 4)
    @Description("Create New Account Company Failed ")

    public void tc_004_newAccount(JSONObject testData) throws Exception {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        WebDriver driver = SeleniumDriver.getInstance().getDriver();
        RegisterPO register = new RegisterPO();
        HomePagePO gologin = new HomePagePO();
        gologin.clickLogin();
        register.registratiClick();
        register.aziendaClick();
        register.genderClick();
        register.maleClick();
        register.settoreClick();
        Thread.sleep(5000);
        register.enterFormCompany(testData.get("ragioneSociale").toString(), testData.get("nome").toString(), testData.get("cognome").toString(),
            timestamp.getDateTime() + testData.get("email").toString() + ".it", testData.get("password").toString(), testData.get("numeroditelefono").toString(), testData.get("cap").toString());
        register.selectStore();
        Thread.sleep(6000);
        register.concludiRegistrazione();

    }


}

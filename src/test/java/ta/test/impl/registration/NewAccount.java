package ta.test.impl.registration;

import com.sun.jmx.snmp.Timestamp;
import io.qameta.allure.Description;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import ta.dataproviders.JSONDataProvider;
import ta.driver.SeleniumDriver;
import ta.pageobjects.impl.HomePagePO;
import ta.pageobjects.impl.LoginPO;
import ta.pageobjects.impl.RegisterPO;
import ta.test.BaseTest;

import java.time.Duration;


public class NewAccount extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(NewAccount.class);

    @Test(dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class, priority = 0)
    @Description("Create New Account Private ")
    public void tc_001_newAccount(JSONObject testData) throws Exception {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        WebDriver driver = SeleniumDriver.getInstance().getDriver();
        RegisterPO register = new RegisterPO();
        LoginPO user = new LoginPO();
        HomePagePO goLogin = new HomePagePO();
        //goLogin.clickLogin(); menu dropdown!
        driver.navigate().to("https://www-qa3.leroymerlin.it/registrazione");
        register.genderClick();
        register.maleClick();
        register.enterUsernameAndPassword(testData.get("nome").toString(), testData.get("cognome").toString(),
            timestamp.getDateTime() + testData.get("email").toString()
                + ".it", testData.get("password").toString(), testData.get("numeroditelefono").toString(), testData.get("cap").toString());
        register.selectStore();
        register.continueClick();
        register.clickrifiuto();
        register.accettaTermini();
        register.accettaProfilazione();
        register.concludiRegistrazione();
        Thread.sleep(10000);
        register.goProfileButton();
        new WebDriverWait(driver, 30).withTimeout(Duration.ofMillis(6000));
        user.logout();
    }


    @Test(dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class, priority = 1)
    @Description("Create New Account Azienda")

    public void tc_002_newAccount(JSONObject testData) throws Exception {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        WebDriver driver = SeleniumDriver.getInstance().getDriver();
        RegisterPO register = new RegisterPO();
        HomePagePO goLogin = new HomePagePO();
        LoginPO user = new LoginPO();
        //goLogin.clickLogin(); menu dropdown!
        driver.navigate().to("https://www-qa3.leroymerlin.it/registrazione");
        register.aziendaClick();
        allSelectClickCompany();
        register.enterForCompany(testData.get("ragioneSociale").toString(), testData.get("cognome").toString(), testData.get("nome").toString(),
            timestamp.getDateTime() + testData.get("email").toString()
                + ".it", testData.get("password").toString(), testData.get("telefono").toString(), testData.get("cap").toString());
        register.selectStore();
        register.continueClick();
        register.accettaButton();
        register.accettaTermini();
        register.accettaProfilazione();
        register.concludiRegistrazione();
        Thread.sleep(20000);
        register.goProfileButton();
        new WebDriverWait(driver, 60).withTimeout(Duration.ofMillis(10000));
        user.logout();
    }

    private static void allSelectClickCompany() throws Exception {
        WebDriver driver = SeleniumDriver.getInstance().getDriver();
        logger.info("---> Select Registration Page Click Start <----");
        Thread.sleep(1000);
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Titolo*'])[1]/following::div[3]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Info societarie'])[1]/following::li[1]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Forma legale*'])[1]/following::div[3]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='SPA'])[1]/following::li[1]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Settore*'])[1]/following::div[3]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Bancario/Finanziario'])[1]/following::li[1]")).click();
        Thread.sleep(1000);
        logger.info("--> Select Click Finish <---");
    }

}

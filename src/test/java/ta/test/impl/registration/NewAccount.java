package ta.test.impl.registration;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.UnsupportedEncodingException;
import java.time.Duration;
import java.util.Date;
import io.qameta.allure.Description;
import ta.dataproviders.JSONDataProvider;
import ta.driver.SeleniumDriver;
import ta.pageobjects.impl.HomePagePO;
import ta.pageobjects.impl.LoginPO;
import ta.pageobjects.impl.RegisterPO;
import ta.test.BaseTest;
import static ta.utilities.constants.Constants.Url.BASE_URL;
import static javafx.scene.input.DataFormat.URL;


public class NewAccount extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(NewAccount.class);

    @Test(dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class, priority = 0)
    @Description("Create New Account Private ")
    public void tc_001_newAccount(JSONObject testData) throws UnsupportedEncodingException, InterruptedException {

        Date data = new Date();
        WebDriver driver = SeleniumDriver.getInstance().getDriver();
        RegisterPO register = new RegisterPO();
        LoginPO user = new LoginPO();
        HomePagePO goLogin = new HomePagePO();
        goLogin.clickLogin();
        register.registratiClick();
        register.genderClick();
        register.maleClick();
        register.enterUsernameAndPassword(testData.get("nome").toString(), testData.get("cognome").toString(),
                data.getTime() + testData.get("email").toString()
                        + ".it", testData.get("password").toString(), testData.get("numeroditelefono").toString(), testData.get("cap").toString());
        register.selectStore();
        register.continueClick();
        register.clickrifiuto();
        register.accettaTermini();
        register.accettaProfilazione();
        register.concludiRegistrazione();
        Thread.sleep(10000);
        String URL = driver.getCurrentUrl();
        Assert.assertEquals(URL, BASE_URL+"/registrazione/benvenuto");
        register.goProfileButton();
        new WebDriverWait(driver, 30).withTimeout(Duration.ofMillis(6000));
        user.logout();
    }


    @Test(dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class, priority = 1)
    @Description("Create New Account Azienda")

    public void tc_002_newAccount(JSONObject testData) throws InterruptedException, UnsupportedEncodingException {

        Date data = new Date();
        WebDriver driver = SeleniumDriver.getInstance().getDriver();
        RegisterPO register = new RegisterPO();
        HomePagePO goLogin = new HomePagePO();
        LoginPO user = new LoginPO();
        goLogin.clickLogin();
        register.aziendaClick();
        allSelectClickCompany();
        register.enterForCompany(testData.get("ragioneSociale").toString(), testData.get("cognome").toString(), testData.get("nome").toString(),
                data.getTime() + testData.get("email").toString()
                        + ".it", testData.get("password").toString(), testData.get("telefono").toString(), testData.get("cap").toString());
        register.selectStore();
        register.continueClick();
        register.accettaButton();
        register.accettaTermini();
        register.accettaProfilazione();
        register.concludiRegistrazione();
        String URL = driver.getCurrentUrl();
        Assert.assertEquals(URL, BASE_URL+"/registrazione/benvenuto");
        register.goProfileButton();
        new WebDriverWait(driver, 30).withTimeout(Duration.ofMillis(6000));
        user.logout();
    }

    private static void allSelectClickCompany() throws InterruptedException {
        WebDriver driver = SeleniumDriver.getInstance().getDriver();
        logger.info("---> Select Registration Page Click Start <----");
        Thread.sleep(3000);
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Titolo*'])[1]/following::div[3]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Info societarie'])[1]/following::li[1]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Forma legale*'])[1]/following::div[3]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='SPA'])[1]/following::li[1]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Settore*'])[1]/following::div[3]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Bancario/Finanziario'])[1]/following::li[1]")).click();
        Thread.sleep(3000);
        logger.info("--> Select Click Finish <---");
    }

}

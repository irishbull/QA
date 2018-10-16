package ta.test.impl.registration;

import com.sun.jmx.snmp.Timestamp;
import io.qameta.allure.Description;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import ta.dataproviders.JSONDataProvider;
import ta.driver.SeleniumDriver;
import ta.pageobjects.impl.HomePagePO;
import ta.pageobjects.impl.RegisterPO;
import ta.test.BaseTest;

import static org.testng.Assert.assertEquals;
import static ta.utilities.constants.Constants.Url.BASE_URL;

public class NewAccount extends BaseTest {

    @SuppressWarnings("all")
    @Test( dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class )
    @Description("Create New Account Private ")

    public void tc_001_newAccount(JSONObject testData)throws Exception {

        /*boolean bull=false;
        while (!bull)*/ {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            WebDriver driver = SeleniumDriver.getInstance().getDriver();
            RegisterPO obj = new RegisterPO();
            HomePagePO gologin = new HomePagePO();
            Thread.sleep(5000);
            gologin.clickLogin();
            obj.registratiClick();
            obj.selPrivatoClick();
            obj.genderClick();
            obj.maleClick();
            obj.enterUsernameAndPassword(testData.get("nome").toString(), testData.get("cognome").toString(),timestamp.getDateTime()+
                testData.get("email").toString() +".it", testData.get("password").toString(), testData.get("numeroditelefono").toString(), testData.get("cap").toString());
            obj.selectStore();
            obj.continueClick();
            obj.clickrifiuto();
            obj.accettaTermini();
            obj.profilo();
            obj.concludiRegistrazione();
            assertEquals(SeleniumDriver.getInstance().getDriver().getCurrentUrl(), BASE_URL+testData.get("landingurl"), "Page url");
            Thread.sleep(5000);
        }

    }

}



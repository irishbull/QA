package ta.test.impl.profile;

import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import io.qameta.allure.Description;
import ta.dataproviders.JSONDataProvider;
import ta.driver.SeleniumDriver;
import ta.pageobjects.impl.HomePagePO;
import ta.pageobjects.impl.LoginPO;
import ta.pageobjects.impl.ProfilePO;
import ta.test.BaseTest;
import ta.utilities.BrowserUtils;
import ta.utilities.SessionStorage;

import static ta.utilities.constants.Constants.Url.BASE_URL;

public class ProfileTest extends BaseTest {

    @Test(dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class , priority = 1)
    @Description("Test Private Profile")

    public void tc_001_Profile(JSONObject testData) throws InterruptedException {

        WebDriver driver = SeleniumDriver.getInstance().getDriver();
        driver.get(BASE_URL);
        HomePagePO goLogin = new HomePagePO();
        ProfilePO profile = new ProfilePO();
        LoginPO loginPO = new LoginPO();
        goLogin.clickLogin();
        loginPO.enterUsernameAndPassword(testData.get("username").toString(), testData.get("password").toString());
        loginPO.clickAccedi();
        BrowserUtils.waitForURLContains("mylm", 30);
        profile.clickProfiloMattonella();
        profile.clickChipNumber();
        profile.clickCodFiscale();
        profile.clickSaveButton();
        try {
            if (BrowserUtils.elementContainsText(profile.toast(), 10, "Operazione completata con successo")
                == true) {
                driver.navigate().to(BASE_URL+"/mylm");
                loginPO.logout();
                SeleniumDriver.getInstance().getDriver().manage().deleteAllCookies();
                SessionStorage.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test(dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class , priority=2)
    @Description("Test Company Profile")

    public void tc_002_Profile(JSONObject testData) throws InterruptedException {

        WebDriver driver = SeleniumDriver.getInstance().getDriver();
        driver.get(BASE_URL);
        HomePagePO goLogin = new HomePagePO();
        ProfilePO profile = new ProfilePO();
        LoginPO loginPO = new LoginPO();
        goLogin.clickLogin();
        loginPO.enterUsernameAndPassword(testData.get("username").toString(), testData.get("password").toString());
        loginPO.clickAccedi();
        BrowserUtils.waitForURLContains("mylm",100);
        profile.clickProfiloMattonella();
        profile.clickChipNumber();
        profile.clickPartitaIva();
        profile.sendPartitaIva();
        profile.clickSaveButton();
        try {
            if (BrowserUtils.elementContainsText(profile.toast(), 10, "Operazione completata con successo")
                == true) {
                SeleniumDriver.getInstance().getDriver().manage().deleteAllCookies();
                SessionStorage.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

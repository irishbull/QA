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

    public void tc_001_PrivateProfile(JSONObject testData) throws InterruptedException {

        WebDriver driver = SeleniumDriver.getInstance().getDriver();
        driver.get(BASE_URL);
        HomePagePO goLogin = new HomePagePO();
        ProfilePO profile = new ProfilePO();
        LoginPO loginPO = new LoginPO();
        goLogin.clickLogin();
        loginPO.enterUsernameAndPassword(testData.get("username").toString(), testData.get("password").toString());
        loginPO.clickAccedi();
        BrowserUtils.waitForURLContains("/mylm",30);
        profile.clickProfiloMattonella();
        profile.clickChipNumber();
        profile.clickCodFiscale();
        profile.clickSaveButton();
        BrowserUtils.elementContainsText(profile.toast(),10,"Operazione completata con successo");
        SeleniumDriver.getInstance().getDriver().manage().deleteAllCookies();
        SessionStorage.clear();
    }

    @Test(dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class , priority=2)
    @Description("Test Company Profile")
    public void tc_002_CompanyProfile(JSONObject testData) throws InterruptedException {

        WebDriver driver = SeleniumDriver.getInstance().getDriver();
        driver.get(BASE_URL);
        HomePagePO goLogin = new HomePagePO();
        ProfilePO profile = new ProfilePO();
        LoginPO loginPO = new LoginPO();
        goLogin.clickLogin();
        loginPO.enterUsernameAndPassword(testData.get("username").toString(), testData.get("password").toString());
        loginPO.clickAccedi();
        BrowserUtils.waitForURLContains("/mylm",30);
        profile.clickProfiloMattonella();
        profile.clickChipNumber();
        profile.clickCodFiscale();
        profile.clickSaveButton();
        BrowserUtils.elementContainsText(profile.toast(),10,"Operazione completata con successo");
        SeleniumDriver.getInstance().getDriver().manage().deleteAllCookies();
        SessionStorage.clear();
    }



    @Test(dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class , priority = 3)
    @Description("Test Private Profile - Cambio Password")

    public void tc_003_PrivateProfileChangePassword(JSONObject testData) throws InterruptedException {

        WebDriver driver = SeleniumDriver.getInstance().getDriver();
        driver.get(BASE_URL);
        HomePagePO goLogin = new HomePagePO();
        ProfilePO profile = new ProfilePO();
        LoginPO loginPO = new LoginPO();
        goLogin.clickLogin();
        loginPO.enterUsernameAndPassword(testData.get("username").toString(), testData.get("password").toString());
        loginPO.clickAccedi();
        BrowserUtils.waitForURLContains("/mylm",30);
        profile.clickProfiloMattonella();
        profile.clickPassword();
        profile.clickCodFiscale();
        profile.clickSaveButton();
        BrowserUtils.elementContainsText(profile.toast(),10,"Operazione completata con successo");
        SeleniumDriver.getInstance().getDriver().manage().deleteAllCookies();
        SessionStorage.clear();
    }
}

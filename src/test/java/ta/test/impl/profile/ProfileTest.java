package ta.test.impl.profile;

import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import ta.dataproviders.JSONDataProvider;
import ta.driver.SeleniumDriver;
import ta.pageobjects.impl.HomePagePO;
import ta.pageobjects.impl.LoginPO;
import ta.pageobjects.impl.ProfilePO;
import ta.test.BaseTest;
import ta.utilities.BrowserUtils;

import static ta.utilities.constants.Constants.Url.BASE_URL;

public class ProfileTest extends BaseTest {

    @Test(dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class)
    @Description("Test Profile")

    public void tc_001_loginSuccess(JSONObject testData) throws InterruptedException {

        WebDriver driver = SeleniumDriver.getInstance().getDriver();
        driver.get(BASE_URL);
        HomePagePO goLogin = new HomePagePO();
        ProfilePO profile = new ProfilePO();
        LoginPO loginPO = new LoginPO();
        goLogin.clickLogin();
        loginPO.enterUsernameAndPassword(testData.get("username").toString(), testData.get("password").toString());
        loginPO.clickAccedi();
        driver.navigate().to((BASE_URL) + testData.get("urlProfile").toString());
        Thread.sleep(20000);
        profile.clickChipEmail();
        profile.clickSaveButton();
        BrowserUtils.elementContainsText(profile.toast(),10,"Operazione completata con successo");
    }

    public void tc_002_loginSuccess(JSONObject testData) throws InterruptedException {

        WebDriver driver = SeleniumDriver.getInstance().getDriver();
        driver.get(BASE_URL);
        HomePagePO goLogin = new HomePagePO();
        ProfilePO profile = new ProfilePO();
        LoginPO loginPO = new LoginPO();
        goLogin.clickLogin();
        loginPO.enterUsernameAndPassword(testData.get("username").toString(), testData.get("password").toString());
        loginPO.clickAccedi();
        Thread.sleep(9000);
        driver.navigate().to((BASE_URL) + testData.get("urlProfile").toString());
        Thread.sleep(20000);
        profile.clickChipEmail();
        profile.clickSaveButton();
        BrowserUtils.elementContainsText(profile.toast(),10,"Operazione completata con successo");
    }

}

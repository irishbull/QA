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

import static ta.utilities.constants.Constants.Url.BASE_URL;

public class ProfileTest extends BaseTest {

    @Test(dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class)
    @Description("Test Profile")

    public void tc_001_loginSuccess(JSONObject testData) throws InterruptedException {

        WebDriver driver = SeleniumDriver.getInstance().getDriver();
        driver.get(BASE_URL);
        HomePagePO homePagePO = new HomePagePO();
        ProfilePO profile = new ProfilePO();
        LoginPO loginPO = new LoginPO();
        driver.navigate().to("https://www-qa3.leroymerlin.it/login?redirectUrl=https://www-qa3.leroymerlin.it/");
        loginPO.enterUsernameAndPassword(testData.get("username").toString(), testData.get("password").toString());
        loginPO.clickcookie();
        loginPO.clickAccedi();
        Thread.sleep(5000);
        driver.navigate().to((BASE_URL) + testData.get("urlProfile").toString());
        Thread.sleep(20000);
        profile.clickChipEmail();
        profile.clickSaveButton();

    }
}

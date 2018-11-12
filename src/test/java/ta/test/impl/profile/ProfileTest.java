package ta.test.impl.profile;

import io.qameta.allure.Description;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import ta.dataproviders.JSONDataProvider;
import ta.driver.SeleniumDriver;
import ta.pageobjects.impl.HomePagePO;
import ta.pageobjects.impl.LoginPO;
import ta.pageobjects.impl.ProfilePO;
import ta.test.BaseTest;
import ta.utilities.BrowserUtils;
import ta.utilities.constants.Constants;

import static ta.utilities.constants.Constants.Url.BASE_URL;

public class ProfileTest extends BaseTest {

    @Test(dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class)
    @Description("Test Profile")

    public void tc_001_loginSuccess(JSONObject testData) throws Exception {

        WebDriver driver = SeleniumDriver.getInstance().getDriver();
        driver.get(BASE_URL);
        HomePagePO homePagePO = new HomePagePO();
        ProfilePO profile = new ProfilePO();
        LoginPO loginPO = homePagePO.clickLoginIconLink();
        loginPO.enterUsernameAndPassword(testData.get("username").toString(), testData.get("password").toString());
        loginPO.clickcookie();
        loginPO.clickAccedi();
        driver.navigate().to((BASE_URL)+ testData.get("urlProfile").toString());
        profile.clickChipProfile();
        profile.clickCodFiscale();
        profile.clickSaveButton();



    }
}

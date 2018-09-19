package ta.test.impl;

import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import ta.dataproviders.JSONDataProvider;
import ta.driver.SeleniumDriver;
import ta.pageobjects.impl.HomePagePO;
import ta.pageobjects.impl.ProductMenuPO;
import ta.test.BaseTest;
import ta.utilities.BrowserUtils;
import ta.utilities.constants.Constants;

import static org.testng.Assert.assertTrue;
import static ta.utilities.constants.Constants.Url.BASE_URL;

public class ProductMenuTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(ProductMenuTest.class);

    @Test(dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class)
    @Description("Verifica la navigazione del menu")
    public void tc_001_navigateProductMenu(JSONObject testData) throws Exception {

        WebDriver driver = SeleniumDriver.getInstance().getDriver();

        driver.get(BASE_URL);

        HomePagePO homePagePO = new HomePagePO();

        if (BrowserUtils.exists(homePagePO.getAcceptCookiesButton(), Constants.WaitTime.EXPLICIT_WAIT)) {
            homePagePO.getAcceptCookiesButton().click();
        }
        ProductMenuPO productMenuPO = new ProductMenuPO();

        // Menu
        BrowserUtils.hover(productMenuPO.getProductMenuWrapper());

        // MacroCategoryGroup
        productMenuPO.mouseHoverMacroCategoryGroup(testData.get("macroCategoryGroup").toString());
        logger.debug("MacroCategoryGroup {} OK", testData.get("macroCategoryGroup"));

        // MacroCategory
        productMenuPO.mouseHoverMacroCategory(testData.get("macroCategory").toString());
        logger.debug("MacroCategory {} OK", testData.get("macroCategory"));

        // Category
        productMenuPO.clickCategory(testData.get("category").toString());
        logger.debug("Category {} OK", testData.get("category"));

        assertTrue(driver.getCurrentUrl().contains(testData.get("pagePath").toString()));
    }
}

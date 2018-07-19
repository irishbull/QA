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
import ta.utilities.Constants;
import ta.utilities.ReadPropertiesFile;

import static org.testng.Assert.assertTrue;

public class ProductMenuTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(ProductMenuTest.class);

    @Test(dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class)
    @Description("Verifica la navigazione del menu")
    public void tc_001_navigateProductMenu(JSONObject testData) throws Exception {

        logger.debug("thread-id:{}", String.valueOf(Thread.currentThread().getId()));
        logger.info(testData.get("description").toString());

        WebDriver driver = SeleniumDriver.getInstance().getDriver();

        driver.get(ReadPropertiesFile.getProperty("qa.base.url"));

        HomePagePO homePagePO = new HomePagePO();

        if (BrowserUtils.exists(homePagePO.getAcceptCookiesButton(), Constants.WaitTime.EXPLICIT_WAIT)) {
            homePagePO.getAcceptCookiesButton().click();
        }
        ProductMenuPO productMenuPO = new ProductMenuPO();

        // Menu
        BrowserUtils.hover(productMenuPO.getProductMenuWrapper());

        // MacroCategoryGroup
        productMenuPO.mouseEnterMacroCategoryGroup(testData.get("macroCategoryGroup").toString());
        logger.debug("MacroCategoryGroup " + testData.get("macroCategoryGroup").toString() + " OK");

        // MacroCategory
        productMenuPO.mouseEnterMacroCategory(testData.get("macroCategory").toString());
        logger.debug("MacroCategory " + testData.get("macroCategory").toString() + " OK");

        // Category
        productMenuPO.clickCategory(testData.get("category").toString());
        logger.debug("Category " + testData.get("category").toString() + " OK");

        assertTrue(driver.getCurrentUrl().contains(testData.get("pagePath").toString()));
    }
}

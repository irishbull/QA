package ta.test.impl.tooso.analytics;

import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;

import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.qameta.allure.Description;
import ta.dataproviders.JSONDataProvider;
import ta.driver.SeleniumDriver;
import ta.pageobjects.impl.AddToCartOverlayPO;
import ta.pageobjects.impl.CartPO;
import ta.pageobjects.impl.SerpPO;
import ta.pageobjects.impl.ToosoSearchPO;
import ta.test.tooso.ToosoBaseTest;
import ta.utilities.ToosoAnalyticsUtils;

import static ta.utilities.constants.Constants.Url.BASE_URL;
import static ta.utilities.constants.ToosoConstants.QUIET_PERIOD;
import static ta.utilities.constants.ToosoConstants.RequestType.ADD_TO_CART;
import static ta.utilities.constants.ToosoConstants.RequestType.REMOVE_FROM_CART;
import static ta.utilities.constants.ToosoConstants.TIMEOUT;

public class ToosoAddToRemoveFromCart extends ToosoBaseTest {
    private static final Logger logger = LoggerFactory.getLogger(ToosoAddToRemoveFromCart.class);

    @Test(priority = 1, dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class)
    @Description("Validate request [type = ADD TO CART] - from serp")
    public void tc_001_verifyAddToCartFromSerp(JSONObject testData) throws Exception {

        logger.info(testData.get("description").toString());

        WebDriver driver = SeleniumDriver.getInstance().getDriver();

        driver.get(BASE_URL + testData.get("pathAndQuery").toString());

        ToosoSearchPO toosoSearchPO = new ToosoSearchPO();

        toosoSearchPO.clickOnSearchTopBar();

        String word = testData.get("search").toString();

        toosoSearchPO.erasePopupSearchInput();

        toosoSearchPO.enterWord(word);

        SerpPO serpPO = toosoSearchPO.search();

        AddToCartOverlayPO addToCartOverlayPO = serpPO.addToCart(testData.get("productCardIndex").toString());

        addToCartOverlayPO.goToCart();

        // wait for quiescence
        SeleniumDriver.getInstance().getProxy().waitForQuiescence(QUIET_PERIOD, TIMEOUT, TimeUnit.SECONDS);

        Har har = SeleniumDriver.getInstance().getProxy().getHar();

        List<HarEntry> entries = ToosoAnalyticsUtils.retrieveEntriesOfType(har.getLog().getEntries(), ADD_TO_CART);

        Assert.assertEquals(entries.size(), 1, "Number of requests [type = ADD TO CART] captured by proxy:");

        String urlToValidate  = entries.get(0).getRequest().getUrl();
        logger.info("Request [type = {}] to validate -> {}", ADD_TO_CART, urlToValidate);

        // check
        ToosoAnalyticsUtils.checkParameters(urlToValidate, testData, ADD_TO_CART);
    }


    @Test(priority = 2, dependsOnMethods = {"tc_001_verifyAddToCartFromSerp"}, dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class)
    @Description("Validate request [type = REMOVE FROM CART]")
    public void tc_002_verifyRemoveFromCart(JSONObject testData) throws Exception {

        logger.info(testData.get("description").toString());

        CartPO cartPO = new CartPO();

        cartPO.removeItemFromCart();

        // wait for quiescence
        SeleniumDriver.getInstance().getProxy().waitForQuiescence(QUIET_PERIOD, TIMEOUT, TimeUnit.SECONDS);

        Har har = SeleniumDriver.getInstance().getProxy().getHar();

        List<HarEntry> entries = ToosoAnalyticsUtils.retrieveEntriesOfType(har.getLog().getEntries(), REMOVE_FROM_CART);

        Assert.assertEquals(entries.size(), 1, "Number of requests [type = REMOVE FROM CART] captured by proxy:");

        String urlToValidate  = entries.get(0).getRequest().getUrl();
        logger.info("Request [type = {}] to validate -> {}", REMOVE_FROM_CART, urlToValidate);

        // check
        ToosoAnalyticsUtils.checkParameters(urlToValidate, testData, REMOVE_FROM_CART);
    }
}

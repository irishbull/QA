package ta.test.impl.tooso.analytics;

import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;

import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.qameta.allure.Description;
import ta.dataproviders.JSONDataProvider;
import ta.driver.SeleniumDriver;
import ta.pageobjects.impl.SerpPO;
import ta.pageobjects.impl.ToosoSearchPO;
import ta.test.tooso.ToosoBaseTest;
import ta.utilities.ToosoAnalyticsUtils;

import static ta.utilities.constants.Constants.Url.BASE_URL;
import static ta.utilities.constants.ToosoConstants.QUIET_PERIOD;
import static ta.utilities.constants.ToosoConstants.RequestType.CLICK_AFTER_SEARCH;
import static ta.utilities.constants.ToosoConstants.TIMEOUT;


public class ToosoClickAfterSearch extends ToosoBaseTest {

    private static final Logger logger = LoggerFactory.getLogger(ToosoClickAfterSearch.class);

    @Test(dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class)
    @Description("Validate request [type = PRODUCT CLICK AFTER SEARCH] - product without variant")
    public void tc_001_verifyClickAfterSearchRequest(JSONObject testData) throws URISyntaxException {

        logger.info(testData.get("description").toString());

        WebDriver driver = SeleniumDriver.getInstance().getDriver();

        driver.get(BASE_URL + testData.get("pathAndQuery").toString());

        ToosoSearchPO toosoSearchPO = new ToosoSearchPO();

        toosoSearchPO.clickOnSearchTopBar();

        String word = testData.get("search").toString();

        toosoSearchPO.erasePopupSearchInput();

        toosoSearchPO.enterWord(word);

        SerpPO serpPO = toosoSearchPO.search();

        serpPO.clickOnProductCard(testData.get("productCardIndex").toString());

        // wait for quiescence
        SeleniumDriver.getInstance().getProxy().waitForQuiescence(QUIET_PERIOD, TIMEOUT, TimeUnit.SECONDS);

        Har har = SeleniumDriver.getInstance().getProxy().getHar();

        List<HarEntry> entriesOfPageViewType = ToosoAnalyticsUtils.retrieveEntriesOfType(har.getLog().getEntries(), CLICK_AFTER_SEARCH);

        Assert.assertEquals(entriesOfPageViewType.size(), 1, "Number of requests [type = CLICK_AFTER_SEARCH] captured by proxy:");

        String urlToValidate = entriesOfPageViewType.get(0).getRequest().getUrl();
        logger.info("Request [type = {}] to validate -> {}", CLICK_AFTER_SEARCH, urlToValidate);

        // check
        ToosoAnalyticsUtils.checkParameters(urlToValidate, testData, CLICK_AFTER_SEARCH);

    }
}

package ta.test.impl.tooso.analytics;

import io.qameta.allure.Description;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import ta.dataproviders.JSONDataProvider;
import ta.driver.SeleniumDriver;
import ta.pageobjects.impl.SerpPO;
import ta.pageobjects.impl.ToosoSearchPO;
import ta.test.tooso.ToosoBaseTest;
import ta.utilities.ToosoAnalyticsUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static ta.utilities.constants.Constants.Url.BASE_URL;
import static ta.utilities.constants.ToosoConstants.QUIET_PERIOD;
import static ta.utilities.constants.ToosoConstants.RequestType.PAGEVIEW_DETAIL;
import static ta.utilities.constants.ToosoConstants.TIMEOUT;

public class ToosoPageViewDetail extends ToosoBaseTest {

    private static Logger logger = LoggerFactory.getLogger(ToosoPageViewDetail.class);

    @Test(dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class)
    @Description("Validate request [type = PAGEVIEW DETAIL] - without variant")
    private void tc_001_verifyPageViewDetail(JSONObject testData) throws Exception{

        logger.info(testData.get("description").toString());

        WebDriver driver = SeleniumDriver.getInstance().getDriver();

        driver.get((BASE_URL) + testData.get("pathAndQuery").toString());

        SerpPO serpPO = new SerpPO();

        serpPO.clickOnProductCard(testData.get("productCardIndex").toString());

        commonCode(testData);

    }

    @Test(dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class)
    @Description("Validate request [type = PAGEVIEW DETAIL] -  with variant")
    private void tc_002_verifyPageViewDetail (JSONObject testData) throws Exception{

        logger.info(testData.get("description").toString());

        WebDriver driver = SeleniumDriver.getInstance().getDriver();

        ToosoSearchPO toosoSearchPO = new ToosoSearchPO();

        SerpPO serpPO = new SerpPO();

        driver.get((BASE_URL) + testData.get("pathAndQuery").toString());

        toosoSearchPO.clickOnSearchTopBar();

        String word = testData.get("search").toString();

        toosoSearchPO.enterWord(word);

        toosoSearchPO.search();

        serpPO.clickOnProductCard(testData.get("productCardIndex").toString());

        commonCode(testData);

    }

    private void commonCode (JSONObject testData) throws Exception{

        SeleniumDriver.getInstance().getProxy().waitForQuiescence(QUIET_PERIOD, TIMEOUT, TimeUnit.SECONDS);

        Har har = SeleniumDriver.getInstance().getProxy().getHar();

        List<HarEntry> entriesOfPageViewType = ToosoAnalyticsUtils.retrieveEntriesOfType(har.getLog().getEntries(), PAGEVIEW_DETAIL);

        Assert.assertEquals(entriesOfPageViewType.size(), 1, "Number of requests [type = PAGEVIEW_DETAIL] captured by proxy:");

        String urlToValidate  = entriesOfPageViewType.get(0).getRequest().getUrl();
        logger.info("Request [type = {}] to validate -> {}", PAGEVIEW_DETAIL, urlToValidate);

        // check
        ToosoAnalyticsUtils.checkParameters(urlToValidate, testData, PAGEVIEW_DETAIL);

    }
}

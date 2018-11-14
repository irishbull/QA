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
import ta.test.tooso.ToosoBaseTest;
import ta.utilities.ToosoAnalyticsUtils;

import static ta.utilities.constants.Constants.Url.BASE_URL;
import static ta.utilities.constants.ToosoConstants.QUIET_PERIOD;
import static ta.utilities.constants.ToosoConstants.RequestType.PAGEVIEW;
import static ta.utilities.constants.ToosoConstants.TIMEOUT;


public class ToosoPageViewTest extends ToosoBaseTest {

    private static final Logger logger = LoggerFactory.getLogger(ToosoPageViewTest.class);

    @Test(dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class)
    @Description("GET [type = PAGEVIEW] - validate request")
    public void tc_001_verifyPageViewRequest(JSONObject testData) throws Exception {

        logger.info(testData.get("description").toString());

        WebDriver driver = SeleniumDriver.getInstance().getDriver();

        driver.get(BASE_URL + testData.get("pathAndQuery").toString());

        // wait for quiescence
        SeleniumDriver.getInstance().getProxy().waitForQuiescence(QUIET_PERIOD, TIMEOUT, TimeUnit.SECONDS);

        Har har = SeleniumDriver.getInstance().getProxy().getHar();

        List<HarEntry> entriesOfPageViewType = ToosoAnalyticsUtils.retrieveEntriesOfType(har.getLog().getEntries(), PAGEVIEW);

        Assert.assertEquals(entriesOfPageViewType.size(), 1, "Number of requests [type = PAGEVIEW] captured by proxy:");

        String urlToValidate  = entriesOfPageViewType.get(0).getRequest().getUrl();
        logger.info("Request [type = {}] to validate -> {}", PAGEVIEW, urlToValidate);

        // check
        ToosoAnalyticsUtils.checkParameters(urlToValidate, testData, PAGEVIEW);
    }
}

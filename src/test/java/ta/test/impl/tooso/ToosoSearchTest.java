package ta.test.impl.tooso;

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
import ta.pageobjects.impl.ToosoSearchPO;
import ta.test.ToosoBaseTest;
import ta.utilities.ReadPropertiesFile;
import ta.utilities.ToosoAnalyticsUtils;

import static ta.utilities.constants.ToosoConstants.QUIET_PERIOD;
import static ta.utilities.constants.ToosoConstants.RequestType.SEARCH;
import static ta.utilities.constants.ToosoConstants.TIMEOUT;

public class ToosoSearchTest extends ToosoBaseTest {

    private static final Logger logger = LoggerFactory.getLogger(ToosoSearchTest.class);

    @Test(dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class)
    @Description("GET [type = SEARCH] - validate request")
    public void tc_001_verifySearchRequest(JSONObject testData) throws Exception {

        String description = testData.get("description").toString();

        logger.info(description);

        WebDriver driver = SeleniumDriver.getInstance().getDriver();

        driver.get(ReadPropertiesFile.getProperty("base.url") + testData.get("path").toString());

        ToosoSearchPO toosoSearchPO = new ToosoSearchPO();

        toosoSearchPO.clickOnSearchBar();

        String word = testData.get("search").toString();

        toosoSearchPO.enterWord(word);

        toosoSearchPO.search();

        // wait for quiescence
        SeleniumDriver.getInstance().getProxy().waitForQuiescence(QUIET_PERIOD, TIMEOUT, TimeUnit.SECONDS);

        Har har = SeleniumDriver.getInstance().getProxy().getHar();
        logger.info("Har = {}", har);

        List<HarEntry> capturedEntries = har.getLog().getEntries();


        for(int i = 1; i < capturedEntries.size(); i++) {
            String url = capturedEntries.get(i).getRequest().getUrl();
            logger.info("{} -> {}", i, url);
        }

        List<HarEntry> suggestEntries = ToosoAnalyticsUtils.retrieveEntriesOfType(capturedEntries, SEARCH);

        for(HarEntry entry : suggestEntries) {
            logger.info(entry.getRequest().getUrl());
        }

        Assert.assertEquals(suggestEntries.size(),  1, "Number of requests [type = search] captured by proxy:");

        /*
        // suggest prefix
        ToosoAnalyticsUtils.checkMandatoryValues(suggestEntries.get(0).getRequest().getUrl(), testData, SUGGEST);
        ToosoAnalyticsUtils.checkSuggestQueryParam(suggestEntries.get(0).getRequest().getUrl(), 1, PROXY_SUGGEST_PREFIX);

        // start from index i = 1 to ignore prefix
        for(int i = 1; i < suggestEntries.size(); i++) {
            String url = suggestEntries.get(i).getRequest().getUrl();
            logger.info("{} -> {}", i, url);
            ToosoAnalyticsUtils.checkMandatoryValues(url, testData, SUGGEST);
            ToosoAnalyticsUtils.checkSuggestQueryParam(url, i, word);
        }
        */

        logger.info("Test completed");
    }
}

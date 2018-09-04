package ta.test.impl.tooso.search;

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
import ta.utilities.ToosoAnalyticsUtils;

import static ta.utilities.constants.Constants.Url.BASE_URL;
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

        driver.get(BASE_URL + testData.get("pathAndQuery").toString());

        ToosoSearchPO toosoSearchPO = new ToosoSearchPO();

        toosoSearchPO.clickOnSearchBar();

        String word = testData.get("search").toString();

        toosoSearchPO.eraseSearchInput();

        toosoSearchPO.enterWord(word);

        toosoSearchPO.search();

        // wait for quiescence
        SeleniumDriver.getInstance().getProxy().waitForQuiescence(QUIET_PERIOD, TIMEOUT, TimeUnit.SECONDS);

        Har har = SeleniumDriver.getInstance().getProxy().getHar();

        List<HarEntry> entriesCaptured = har.getLog().getEntries();

        List<HarEntry> entriesToValidate = ToosoAnalyticsUtils.retrieveEntriesOfType(entriesCaptured, SEARCH);

        Assert.assertEquals(entriesToValidate.size(),  1, "Number of requests [type = SEARCH] captured by proxy:");

        String urlToValidate = entriesToValidate.get(0).getRequest().getUrl();

        logger.info("Request [type = {}] to validate -> {}", SEARCH, urlToValidate);

        ToosoAnalyticsUtils.checkMandatoryValues(urlToValidate, testData, SEARCH);
    }
}

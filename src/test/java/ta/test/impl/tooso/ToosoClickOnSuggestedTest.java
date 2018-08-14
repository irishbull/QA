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
import static ta.utilities.constants.ToosoConstants.RequestType.CLICK_ON_SUGGESTED;
import static ta.utilities.constants.ToosoConstants.TIMEOUT;


public class ToosoClickOnSuggestedTest extends ToosoBaseTest {

    private static final Logger logger = LoggerFactory.getLogger(ToosoClickOnSuggestedTest.class);

    @Test(dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class)
    @Description("GET [type = CLICK ON SUGGESTED] - validate request")
    public void tc_001_verifyClickOnSuggestRequest(JSONObject testData) throws Exception {

        String description = testData.get("description").toString();

        logger.info(description);

        WebDriver driver = SeleniumDriver.getInstance().getDriver();

        driver.get(ReadPropertiesFile.getProperty("base.url") + testData.get("path").toString());

        ToosoSearchPO toosoSearchPO = new ToosoSearchPO();

        toosoSearchPO.clickOnSearchBar();

        toosoSearchPO.clickOnFirstResultElement();

        // wait for quiescence
        SeleniumDriver.getInstance().getProxy().waitForQuiescence(QUIET_PERIOD, TIMEOUT, TimeUnit.SECONDS);

        Har har = SeleniumDriver.getInstance().getProxy().getHar();

        List<HarEntry> entriesCaptured = har.getLog().getEntries();

        List<HarEntry> entriesToCheck = ToosoAnalyticsUtils.retrieveEntriesOfType(entriesCaptured, CLICK_ON_SUGGESTED);

        Assert.assertEquals(entriesToCheck.size(), 1, "Number of requests [type = CLICK ON SUGGESTED] captured by proxy:");

        String urlToVerify = entriesToCheck.get(0).getRequest().getUrl();
        logger.info("Request [type = {}] to validate -> {}", CLICK_ON_SUGGESTED,  urlToVerify);

        // check
        ToosoAnalyticsUtils.checkMandatoryValues(urlToVerify, testData, CLICK_ON_SUGGESTED);
    }
}

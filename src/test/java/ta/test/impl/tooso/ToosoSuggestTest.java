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
import ta.pageobjects.impl.HomePagePO;
import ta.pageobjects.impl.ToosoSearchPO;
import ta.test.ToosoBaseTest;
import ta.utilities.ReadPropertiesFile;
import ta.utilities.ToosoAnalyticsUtils;

import static ta.utilities.constants.ToosoConstants.PROXY_SUGGEST_PREFIX;
import static ta.utilities.constants.ToosoConstants.QUIET_PERIOD;
import static ta.utilities.constants.ToosoConstants.RequestType.SUGGEST;
import static ta.utilities.constants.ToosoConstants.TIMEOUT;

public class ToosoSuggestTest extends ToosoBaseTest {

    private static final Logger logger = LoggerFactory.getLogger(ToosoSuggestTest.class);

    @Test(dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class)
    @Description("Verifica i valori dei parametri della richiesta GET")
    public void tc_001_verifySuggestRequest(JSONObject testData) throws Exception {

        String description = testData.get("description").toString();

        logger.info(description);

        WebDriver driver = SeleniumDriver.getInstance().getDriver();

        driver.get(ReadPropertiesFile.getProperty("base.url") + testData.get("path").toString());

        HomePagePO homePagePO = new HomePagePO();

        ToosoSearchPO toosoSearchPO = homePagePO.clickOnSearchBar();

        String word = testData.get("search").toString();

        toosoSearchPO.enterWord(word);

        // wait for quiescence
        SeleniumDriver.getInstance().getProxy().waitForQuiescence(QUIET_PERIOD, TIMEOUT, TimeUnit.SECONDS);

        Har har = SeleniumDriver.getInstance().getProxy().getHar();
        logger.info("Har = {}", har);

        List<HarEntry> capturedEntries = har.getLog().getEntries();

        List<HarEntry> suggestEntries = ToosoAnalyticsUtils.retrieveEntriesOfType(capturedEntries, SUGGEST);

        for(HarEntry entry : suggestEntries) {
            logger.info(entry.getRequest().getUrl());
        }

        Assert.assertEquals(suggestEntries.size(), word.length() + 1, "Number of suggest requests captured by proxy:");

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

        logger.info("Test completed");
    }
}

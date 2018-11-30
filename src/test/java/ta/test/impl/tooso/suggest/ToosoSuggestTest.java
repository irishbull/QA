package ta.test.impl.tooso.suggest;

import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;

import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import io.qameta.allure.Description;
import ta.dataproviders.JSONDataProvider;
import ta.driver.SeleniumDriver;
import ta.pageobjects.impl.ToosoSearchAngularPO;
import ta.pageobjects.impl.ToosoSearchPO;
import ta.test.tooso.ToosoBaseTest;
import ta.utilities.ToosoAnalyticsUtils;

import static ta.utilities.constants.Constants.Url.BASE_URL;
import static ta.utilities.constants.ToosoConstants.PROXY_SUGGEST_PREFIX;
import static ta.utilities.constants.ToosoConstants.QUIET_PERIOD;
import static ta.utilities.constants.ToosoConstants.RequestType.SUGGEST;
import static ta.utilities.constants.ToosoConstants.TIMEOUT;

public class ToosoSuggestTest extends ToosoBaseTest {

    private static final Logger logger = LoggerFactory.getLogger(ToosoSuggestTest.class);

    // REACT
    @Test(dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class)
    @Description("GET [type = SUGGEST] - validate requests (react)")
    public void tc_001_verifySuggestRequest(JSONObject testData) throws URISyntaxException {

        logger.info(testData.get("description").toString());

        WebDriver driver = SeleniumDriver.getInstance().getDriver();

        String url = BASE_URL + testData.get("pathAndQuery").toString();

        driver.get(url);

        ToosoSearchPO toosoSearchPO = new ToosoSearchPO();

        toosoSearchPO.clickOnSearchTopBar();

        String word = testData.get("search").toString();

        toosoSearchPO.enterWord(word);

        // wait for quiescence
        SeleniumDriver.getInstance().getProxy().waitForQuiescence(QUIET_PERIOD, TIMEOUT, TimeUnit.SECONDS);

        Har har = SeleniumDriver.getInstance().getProxy().getHar();

        List<HarEntry> entriesCaptured = har.getLog().getEntries();

        List<HarEntry> entriesToValidate = ToosoAnalyticsUtils.retrieveEntriesOfType(entriesCaptured, SUGGEST);

        Assert.assertEquals(entriesToValidate.size(), word.length() + 1, String.format("Page[%s]\n Number of requests [type = SUGGEST] captured by proxy:", url));

        List<String> errorMessages = new ArrayList<>();

        // check the first entry (q=*)
        errorMessages.addAll(ToosoAnalyticsUtils.queryParamsValidation(entriesToValidate.get(0).getRequest().getUrl(), testData, SUGGEST));
        errorMessages.addAll(ToosoAnalyticsUtils.paramQValidation(entriesToValidate.get(0).getRequest().getUrl(), 1, PROXY_SUGGEST_PREFIX));

        // check remaining entries
        for (int i = 1; i < entriesToValidate.size(); i++) {
            String requestUrl = entriesToValidate.get(i).getRequest().getUrl();
            logger.info("{} - Request [type = {}] to validate -> {}", i, SUGGEST, requestUrl);
            errorMessages.addAll(ToosoAnalyticsUtils.queryParamsValidation(requestUrl, testData, SUGGEST));
            errorMessages.addAll(ToosoAnalyticsUtils.paramQValidation(requestUrl, i, word));
        }

        Assert.assertTrue(errorMessages.isEmpty(),
                errorMessages.stream().collect(Collectors.joining("")) + String.format("Page[%s]\n Tooso suggest requests are valid", url));
    }


    // ANGULAR
    @Test(dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class)
    @Description("GET [type = SUGGEST] - validate requests (angular)")
    public void tc_002_verifyAngularSuggestRequest(JSONObject testData) throws URISyntaxException {

        WebDriver driver = SeleniumDriver.getInstance().getDriver();

        String url = BASE_URL + testData.get("pathAndQuery").toString();

        driver.get(url);

        ToosoSearchAngularPO toosoSearchPO = new ToosoSearchAngularPO();

        toosoSearchPO.clickOnSearchTopBar();

        String word = testData.get("search").toString();

        toosoSearchPO.enterWord(word);

        // wait for quiescence
        SeleniumDriver.getInstance().getProxy().waitForQuiescence(QUIET_PERIOD, TIMEOUT, TimeUnit.SECONDS);

        Har har = SeleniumDriver.getInstance().getProxy().getHar();

        List<HarEntry> entriesCaptured = har.getLog().getEntries();

        List<HarEntry> entriesToValidate = ToosoAnalyticsUtils.retrieveEntriesOfType(entriesCaptured, SUGGEST);

        Assert.assertEquals(entriesToValidate.size(), word.length() + 1, String.format("Page[%s]\n Number of requests [type = SUGGEST] captured by proxy:", url));

        List<String> errorMessages = new ArrayList<>();

        // check the first entry (q=*)
        errorMessages.addAll(ToosoAnalyticsUtils.queryParamsValidation(entriesToValidate.get(0).getRequest().getUrl(), testData, SUGGEST));
        errorMessages.addAll(ToosoAnalyticsUtils.paramQValidation(entriesToValidate.get(0).getRequest().getUrl(), 1, PROXY_SUGGEST_PREFIX));

        // check remaining entries
        for (int i = 1; i < entriesToValidate.size(); i++) {
            String requestUrl = entriesToValidate.get(i).getRequest().getUrl();
            logger.info("{} - Request [type = {}] to validate -> {}", i, SUGGEST, requestUrl);
            errorMessages.addAll(ToosoAnalyticsUtils.queryParamsValidation(requestUrl, testData, SUGGEST));
            errorMessages.addAll(ToosoAnalyticsUtils.paramQValidation(requestUrl, i, word));
        }

        Assert.assertTrue(errorMessages.isEmpty(),
                errorMessages.stream().collect(Collectors.joining("")) + String.format("Page[%s]\n Tooso suggest requests are valid", url));

    }
}

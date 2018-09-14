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
import ta.pageobjects.impl.ProductNavBarPO;
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
    @Description("Validate request [type = SEARCH] from homepage. Event; search")
    public void tc_001_verifyFirstSearchRequest(JSONObject testData) throws Exception {

        logger.info(testData.get("description").toString());

        WebDriver driver = SeleniumDriver.getInstance().getDriver();

        driver.get(BASE_URL + testData.get("pathAndQuery").toString());

        ToosoSearchPO toosoSearchPO = new ToosoSearchPO();

        toosoSearchPO.clickOnSearchTopBar();

        String word = testData.get("search").toString();

        toosoSearchPO.erasePopupSearchInput();

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

        ToosoAnalyticsUtils.checkParameters(urlToValidate, testData, SEARCH);
    }


    @Test(dependsOnMethods = {"tc_001_verifyFirstSearchRequest"}, dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class)
    @Description("Validate request [type = SEARCH] from serp. Event: go to next result page")
    public void tc_002_verifySecondSearchRequest(JSONObject testData) throws Exception {

        logger.info(testData.get("description").toString());

        ToosoSearchPO toosoSearchPO = new ToosoSearchPO();

        toosoSearchPO.scrollToNextPageButton();

        toosoSearchPO.goToNextPage();

        // wait for quiescence
        SeleniumDriver.getInstance().getProxy().waitForQuiescence(QUIET_PERIOD, TIMEOUT, TimeUnit.SECONDS);

        Har har = SeleniumDriver.getInstance().getProxy().getHar();

        List<HarEntry> entriesCaptured = har.getLog().getEntries();

        List<HarEntry> entriesToValidate = ToosoAnalyticsUtils.retrieveEntriesOfType(entriesCaptured, SEARCH);

        Assert.assertEquals(entriesToValidate.size(),  1, "Number of requests [type = SEARCH] captured by proxy:");

        String urlToValidate = entriesToValidate.get(0).getRequest().getUrl();

        logger.info("Request [type = {}] to validate -> {}", SEARCH, urlToValidate);

        ToosoAnalyticsUtils.checkParameters(urlToValidate, testData, SEARCH);
    }


    @Test(dependsOnMethods = {"tc_002_verifySecondSearchRequest"}, dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class)
    @Description("Validate request [type = SEARCH] from serp. Event: apply filter")
    public void tc_003_verifySearchRequestWithFilter(JSONObject testData) throws Exception {

        logger.info(testData.get("description").toString());

        ProductNavBarPO productNavBarPO = new ProductNavBarPO();

        productNavBarPO.applyFilter(testData.get("filterType").toString(), testData.get("filterId").toString());

        // wait for quiescence
        SeleniumDriver.getInstance().getProxy().waitForQuiescence(QUIET_PERIOD, TIMEOUT, TimeUnit.SECONDS);

        Har har = SeleniumDriver.getInstance().getProxy().getHar();

        List<HarEntry> entriesCaptured = har.getLog().getEntries();

        List<HarEntry> entriesToValidate = ToosoAnalyticsUtils.retrieveEntriesOfType(entriesCaptured, SEARCH);

        Assert.assertEquals(entriesToValidate.size(),  1, "Number of requests [type = SEARCH] captured by proxy:");

        String urlToValidate = entriesToValidate.get(0).getRequest().getUrl();

        logger.info("Request [type = {}] to validate -> {}", SEARCH, urlToValidate);

        ToosoAnalyticsUtils.checkParameters(urlToValidate, testData, SEARCH);
    }


    @Test(dependsOnMethods = {"tc_003_verifySearchRequestWithFilter"}, dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class)
    @Description("Validate request [type = SEARCH] from serp. Event: apply sorting")
    public void tc_004_verifySearchRequestWithSorting(JSONObject testData) throws Exception {

        logger.info(testData.get("description").toString());

        ProductNavBarPO productNavBarPO = new ProductNavBarPO();

        productNavBarPO.sortBy(testData.get("sortingType").toString());

        // wait for quiescence
        SeleniumDriver.getInstance().getProxy().waitForQuiescence(QUIET_PERIOD, TIMEOUT, TimeUnit.SECONDS);

        Har har = SeleniumDriver.getInstance().getProxy().getHar();

        List<HarEntry> entriesCaptured = har.getLog().getEntries();

        List<HarEntry> entriesToValidate = ToosoAnalyticsUtils.retrieveEntriesOfType(entriesCaptured, SEARCH);

        Assert.assertEquals(entriesToValidate.size(),  1, "Number of requests [type = SEARCH] captured by proxy:");

        String urlToValidate = entriesToValidate.get(0).getRequest().getUrl();

        logger.info("Request [type = {}] to validate -> {}", SEARCH, urlToValidate);

        ToosoAnalyticsUtils.checkParameters(urlToValidate, testData, SEARCH);
    }


    @Test(dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class)
    @Description("Validate request [type = SEARCH] with typoCorrection = false")
    public void tc_005_verifySearchRequestWithTypoCorrection(JSONObject testData) throws Exception {

        logger.info(testData.get("description").toString());

        WebDriver driver = SeleniumDriver.getInstance().getDriver();

        driver.get(BASE_URL + testData.get("pathAndQuery").toString());

        ToosoSearchPO toosoSearchPO = new ToosoSearchPO();

        toosoSearchPO.clickOnSearchTopBar();

        String typoError = testData.get("search").toString();

        toosoSearchPO.erasePopupSearchInput();

        toosoSearchPO.enterWord(typoError);

        toosoSearchPO.search();

        ProductNavBarPO productNavBarPO = new ProductNavBarPO();

        productNavBarPO.searchWithTypoCorrectionDisabled(testData.get("search").toString());

        // wait for quiescence
        SeleniumDriver.getInstance().getProxy().waitForQuiescence(QUIET_PERIOD, TIMEOUT, TimeUnit.SECONDS);

        Har har = SeleniumDriver.getInstance().getProxy().getHar();

        List<HarEntry> entriesCaptured = har.getLog().getEntries();

        List<HarEntry> sortedEntriesToValidate = ToosoAnalyticsUtils.retrieveEntriesOfType(entriesCaptured, SEARCH);

        Assert.assertEquals(sortedEntriesToValidate.size(),  2, "Number of requests [type = SEARCH] captured by proxy:");

        // validate only the first request (request with typoCorrection=false comes first in alphabetical order)
        String urlToValidate = sortedEntriesToValidate.get(0).getRequest().getUrl();

        logger.info("Request [type = {}] to validate -> {}", SEARCH, urlToValidate);

        ToosoAnalyticsUtils.checkParameters(urlToValidate, testData, SEARCH);
    }

}

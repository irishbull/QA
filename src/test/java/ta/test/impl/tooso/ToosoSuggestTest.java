package ta.test.impl.tooso;

import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;

import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.qameta.allure.Description;
import ta.dataproviders.JSONDataProvider;
import ta.driver.SeleniumDriver;
import ta.pageobjects.impl.HomePagePO;
import ta.pageobjects.impl.ToosoSearchPO;
import ta.test.BaseTest;
import ta.utilities.AnalyticsUtils;
import ta.utilities.Constants;
import ta.utilities.ReadPropertiesFile;

public class ToosoSuggestTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(ToosoSuggestTest.class);

    @BeforeMethod
    public void createHar(Method method) {
        SeleniumDriver.getInstance().getProxy().newHar(method.getName());
        logger.debug("Har {} created for method {}", SeleniumDriver.getInstance().getProxy().getHar(), method.getName());
    }


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
        SeleniumDriver.getInstance().getProxy().waitForQuiescence(2, 10, TimeUnit.SECONDS);

        Har har = SeleniumDriver.getInstance().getProxy().getHar();
        logger.info("Har = {}", har);

        List<HarEntry> toosoEntries = AnalyticsUtils.getSuggestRequestsSorted(har.getLog().getEntries());

        for(HarEntry entry : toosoEntries) {
            logger.info(entry.getRequest().getUrl());
        }

        Assert.assertEquals(toosoEntries.size(), word.length() + 1, "Number of suggest requests captured by proxy:");

        // suggest prefix
        AnalyticsUtils.checkSuggestQueryParam(toosoEntries.get(0).getRequest().getUrl(), 1, Constants.Tooso.PROXY_SUGGEST_PREFIX);
        //AnalyticsUtils.checkMandatoryValues(url, testData);

        // start from index i = 1 to ignore prefix
        for(int i = 1; i < toosoEntries.size(); i++) {
            String url = toosoEntries.get(i).getRequest().getUrl();
            logger.info("{} -> {}", i, url);
            //AnalyticsUtils.checkMandatoryValues(url, testData);
            AnalyticsUtils.checkSuggestQueryParam(url, i, word);
        }

        logger.info("Test completed");
    }
}

package ta.test.impl.tooso;

import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;

import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

import io.qameta.allure.Description;
import ta.dataproviders.JSONDataProvider;
import ta.driver.SeleniumDriver;
import ta.pageobjects.impl.HomePagePO;
import ta.pageobjects.impl.HomePageSearchResultsPO;
import ta.test.BaseTest;
import ta.utilities.AnalyticsUtils;
import ta.utilities.ReadPropertiesFile;


public class ToosoSuggestTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(ToosoSuggestTest.class);

    @BeforeMethod
    public void createHar(Method method) {
        SeleniumDriver.getInstance().getProxy().newHar(method.getName());
        logger.debug("Har {} created for method {}", SeleniumDriver.getInstance().getProxy().getHar(), method.getName());
    }

    @AfterMethod
    public void endHar(Method method) {

        Har har = SeleniumDriver.getInstance().getProxy().getHar();

        if (Objects.nonNull(har)) {
            SeleniumDriver.getInstance().getProxy().endHar();
            logger.debug("Har {} closed for method {}", har, method.getName());
        }
    }


    @Test(dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class)
    @Description("Verifica i valori dei parametri della richiesta GET")
    public void tc_001_verifySuggestRequest(JSONObject testData) throws Exception {

        String description = testData.get("description").toString();

        logger.info(description);

        WebDriver driver = SeleniumDriver.getInstance().getDriver();

        driver.get(ReadPropertiesFile.getProperty("base.url") + testData.get("path").toString());

        HomePagePO homePagePO = new HomePagePO();

        HomePageSearchResultsPO homePageSearchResultsPO = homePagePO.clickOnSearch();

        homePageSearchResultsPO.getFirstListElement().click();

        Thread.sleep(7000);

        Har har = SeleniumDriver.getInstance().getProxy().getHar();

        logger.info("Har entries: {}", har.getLog().getEntries().size());

        logger.info("Har = {}", har);

        List<HarEntry> toosoEntries = AnalyticsUtils.getSuggestRequests(har.getLog().getEntries());

        logger.info("toosoEntries size: {}", toosoEntries.size());

        Assert.assertEquals(toosoEntries.size(), 1, "Number of suggest requests captured by proxy:");

        String url = toosoEntries.get(0).getRequest().getUrl();
        logger.info("URL to check: {}", url);

        // check
        AnalyticsUtils.checkMandatoryValues(url, testData);

        logger.info("Test completed");
    }
}

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
import java.util.concurrent.TimeUnit;

import io.qameta.allure.Description;
import ta.dataproviders.JSONDataProvider;
import ta.driver.SeleniumDriver;
import ta.pageobjects.impl.HomePagePO;
import ta.pageobjects.impl.ToosoSearchPO;
import ta.test.BaseTest;
import ta.utilities.ToosoAnalyticsUtils;
import ta.utilities.ReadPropertiesFile;
import ta.utilities.constants.ToosoConstants;

import static ta.utilities.constants.ToosoConstants.QUIET_PERIOD;
import static ta.utilities.constants.ToosoConstants.RequestType.CLICK_ON_SUGGESTED;
import static ta.utilities.constants.ToosoConstants.TIMEOUT;


public class ToosoClickOnSuggestedTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(ToosoClickOnSuggestedTest.class);

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
    public void tc_001_verifyClickOnSuggestRequest(JSONObject testData) throws Exception {

        String description = testData.get("description").toString();

        logger.info(description);

        WebDriver driver = SeleniumDriver.getInstance().getDriver();

        driver.get(ReadPropertiesFile.getProperty("base.url") + testData.get("path").toString());

        HomePagePO homePagePO = new HomePagePO();

        ToosoSearchPO toosoSearchPO = homePagePO.clickOnSearchBar();

        toosoSearchPO.getFirstResultElement().click();

        // wait for quiescence
        SeleniumDriver.getInstance().getProxy().waitForQuiescence(QUIET_PERIOD, TIMEOUT, TimeUnit.SECONDS);

        Har har = SeleniumDriver.getInstance().getProxy().getHar();

        logger.info("Har entries: {}", har.getLog().getEntries().size());

        logger.info("Har = {}", har);

        List<HarEntry> capturedEntries = har.getLog().getEntries();

        List<HarEntry> entriesToCheck = ToosoAnalyticsUtils.retrieveEntriesOfType(capturedEntries, CLICK_ON_SUGGESTED);

        logger.info("entriesToCheck size: {}", entriesToCheck.size());

        Assert.assertEquals(entriesToCheck.size(), 1, "Number of click on suggest requests captured by proxy:");

        String url = entriesToCheck.get(0).getRequest().getUrl();
        logger.info("URL to check: {}", url);

        // check
        ToosoAnalyticsUtils.checkMandatoryValues(url, testData, CLICK_ON_SUGGESTED);

        logger.info("Test completed");
    }
}

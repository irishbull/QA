package ta.test.impl;

import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;

import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import io.qameta.allure.Description;
import ta.dataproviders.JSONDataProvider;
import ta.driver.SeleniumDriver;
import ta.test.BaseTest;
import ta.utilities.BrowserUtils;
import ta.utilities.Constants;
import ta.utilities.ReadPropertiesFile;


public class ToosoPageViewTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(ToosoPageViewTest.class);

    @BeforeMethod
    public void createHar(Method method) {
        SeleniumDriver.getInstance().getProxy().newHar(method.getName());
        logger.debug("Har {} created for method {}", SeleniumDriver.getInstance().getProxy().getHar(), method.getName());
    }


    @Test(dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class)
    @Description("Verifica i valori dei parametri della richiesta GET")
    public void tc_001_verifyPageViewRequest(JSONObject testData) throws Exception {

        logger.info(testData.get("description").toString());
        logger.info("thread-id:{}", String.valueOf(Thread.currentThread().getId()));

        WebDriver driver = SeleniumDriver.getInstance().getDriver();

        driver.get(ReadPropertiesFile.getProperty("base.url"));

        BrowserUtils.waitForPageFullyLoaded(Constants.WaitTime.EXPLICIT_WAIT);

        Har har = SeleniumDriver.getInstance().getProxy().getHar();
        logger.info("Har = " + SeleniumDriver.getInstance().getProxy().getHar());

        for (HarEntry harEntry : har.getLog().getEntries()) {
            logger.info("HAR ENTRY: " + harEntry.getRequest().getUrl());
        }

        logger.info("Page is fully loaded");
    }

    @Test
    @Description("Verifica i valori dei parametri della richiesta GET")
    public void tc_002_verifyPageViewRequest() throws Exception {

        logger.info("thread-id:{}", String.valueOf(Thread.currentThread().getId()));

        WebDriver driver = SeleniumDriver.getInstance().getDriver();

        driver.get("https://www.leroymerlin.it/catalogo/box-doccia-scorrevole-yavary-36581860-p");

        BrowserUtils.waitForPageFullyLoaded(Constants.WaitTime.EXPLICIT_WAIT);

        Har har = SeleniumDriver.getInstance().getProxy().getHar();
        logger.info("Har = " + SeleniumDriver.getInstance().getProxy().getHar());

        for (HarEntry harEntry : har.getLog().getEntries()) {
            logger.info("HAR ENTRY: " + harEntry.getRequest().getUrl());
        }

        logger.info("Product page is fully loaded");
    }
}

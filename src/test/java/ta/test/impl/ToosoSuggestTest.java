package ta.test.impl;

import io.qameta.allure.Description;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ta.dataproviders.JSONDataProvider;
import ta.driver.SeleniumDriver;
import ta.pageobjects.impl.HomePagePO;
import ta.pageobjects.impl.HomePageSearchResultsPO;
import ta.test.BaseTest;
import ta.utilities.AnalyticsUtils;
import ta.utilities.BrowserUtils;
import ta.utilities.Constants;
import ta.utilities.ReadPropertiesFile;

import java.lang.reflect.Method;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


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

        logger.info(testData.get("description").toString());

        WebDriver driver = SeleniumDriver.getInstance().getDriver();

        driver.get(ReadPropertiesFile.getProperty("base.url") + testData.get("path").toString());

        HomePagePO homePagePO = new HomePagePO();

        HomePageSearchResultsPO homePageSearchResultsPO = homePagePO.clickOnSearch();

        homePageSearchResultsPO.getFirstListElement().click();

        logger.info(String.valueOf(BrowserUtils.elementIsInvisible(homePagePO.getSearchWrapper(), Constants.WaitTime.EXPLICIT_WAIT)));

        //Thread.sleep(7000);

        Har har = SeleniumDriver.getInstance().getProxy().getHar();

        logger.info("Har entries: {}",har.getLog().getEntries().size());

        logger.info("Har = " + SeleniumDriver.getInstance().getProxy().getHar());

        List<HarEntry> toosoEntries = AnalyticsUtils.getSuggestRequests(har.getLog().getEntries());

        logger.info("toosoEntries size: {}",toosoEntries.size());

        Assert.assertTrue(toosoEntries.size() == 1);

        String url  = toosoEntries.get(0).getRequest().getUrl();

        logger.info("URL to be checked: {}", url);

        HashMap<String, String> mandatoryValues = (HashMap<String, String>) testData.get("mandatoryValues");

        HashMap<String, String> notEmptyValues = (HashMap<String, String>) testData.get("notEmptyValues");

        HashMap<String, String> optionalValues = (HashMap<String, String>) testData.get("optionalValues");

        List<NameValuePair> urlNameValuePairs = URLEncodedUtils.parse(new URI(url), Charset.forName(Constants.Encode.UTF_8));

        Map<String, String> mappedValues = urlNameValuePairs.stream().collect(
            Collectors.toMap(NameValuePair::getName, NameValuePair::getValue));

        AnalyticsUtils.checkMandatoryValues(mappedValues, mandatoryValues);

        //AnalyticsUtils.checkNotEmptyValues(urlNameValuePairs, mandatoryValues);

        // AnalyticsUtils.checkOptionalValues(urlNameValuePairs, mandatoryValues);

        logger.info("Test completed");
    }
}

package ta.test.impl.tooso;

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

import java.lang.reflect.Method;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.qameta.allure.Description;
import ta.dataproviders.JSONDataProvider;
import ta.driver.SeleniumDriver;
import ta.test.BaseTest;
import ta.utilities.AnalyticsUtils;
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

        String description = testData.get("description").toString();

        logger.info(description);

        WebDriver driver = SeleniumDriver.getInstance().getDriver();

        driver.get(ReadPropertiesFile.getProperty("base.url") + testData.get("path").toString());


        //BrowserUtils.wait
        Thread.sleep(7000); //To remove


        Har har = SeleniumDriver.getInstance().getProxy().getHar();
        logger.info("Har = {}", har);

        List<HarEntry> toosoEntries = AnalyticsUtils.getPageViewRequests(har.getLog().getEntries());

        Assert.assertEquals(toosoEntries.size(), 1, "Number of pageview requests captured by proxy:");

        String url  = toosoEntries.get(0).getRequest().getUrl();
        logger.info("URL to check: {}", url);

        // json mandatory parameters expected values
        HashMap<String, String> jsonExpectedQueryParams = (HashMap<String, String>) testData.get("mandatoryValues");

        // json parameters that should be not empty
        List<String> jsonNotEmptyParams = (List<String>)testData.get("notEmptyValues");

        // current request query parameters
        List<NameValuePair> urlNameValuePairs = URLEncodedUtils.parse(new URI(url), Charset.forName(Constants.Encode.UTF_8));
        Map<String, String> actualQueryParams = urlNameValuePairs.stream().collect(
            Collectors.toMap(NameValuePair::getName, NameValuePair::getValue));

        // check
        AnalyticsUtils.checkMandatoryValues(actualQueryParams, jsonExpectedQueryParams);
        AnalyticsUtils.checkNotEmptyValues(actualQueryParams, jsonNotEmptyParams);

        logger.info("Test completed");
    }
}

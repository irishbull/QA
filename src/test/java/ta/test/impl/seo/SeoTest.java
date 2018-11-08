package ta.test.impl.seo;

import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import io.qameta.allure.Description;
import ta.dataproviders.JSONDataProvider;
import ta.driver.SeleniumDriver;
import ta.test.ToosoBaseTest;
import ta.test.impl.seo.validation.SeoData;
import ta.test.impl.seo.validation.SeoValidationContext;
import ta.test.impl.seo.validation.stragety.SeoValidationStrategy;
import ta.test.impl.seo.validation.stragety.impl.HtmlSeoValidationStrategy;
import ta.utilities.BrowserUtils;

import static ta.utilities.constants.Constants.Url.BASE_URL;
import static ta.utilities.constants.ToosoConstants.QUIET_PERIOD;
import static ta.utilities.constants.ToosoConstants.TIMEOUT;

public class SeoTest extends ToosoBaseTest {

    private static final Logger logger = LoggerFactory.getLogger(SeoTest.class);

    @Test(dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class)
    @Description("SEO test - analyse source and rendered document")
    public void tc_001_seoTest(JSONObject testData) throws IOException {

        WebDriver driver = SeleniumDriver.getInstance().getDriver();
        
        String url = BASE_URL + testData.get("pagePath").toString();
        logger.info("Analyse page with url {}", url);

        driver.get(url);

        // wait for quiescence
        SeleniumDriver.getInstance().getProxy().waitForQuiescence(QUIET_PERIOD, TIMEOUT, TimeUnit.SECONDS);

        // Rendered
        String renderedHtml = BrowserUtils.getRenderedPage();
        Document renderedDocument = Jsoup.parse(renderedHtml);

        // Source using Jsoup
        Document sourceDocument = Jsoup.connect(url).get();

        Set<SeoValidationStrategy> strategies = new LinkedHashSet<>();

        strategies.add(HtmlSeoValidationStrategy.TITLE);
        strategies.add(HtmlSeoValidationStrategy.HEADER1);
        strategies.add(HtmlSeoValidationStrategy.META_DESCRIPTION);
        strategies.add(HtmlSeoValidationStrategy.REL_CANONICAL);
        strategies.add(HtmlSeoValidationStrategy.ANCHOR);
        strategies.add(HtmlSeoValidationStrategy.NOINDEX);

        SeoValidationContext seoValidationContext = new SeoValidationContext(strategies);

        logger.info("Validate source page");
        SeoData sourceData = new SeoData(sourceDocument, false, testData);
        seoValidationContext.execute(sourceData);

        logger.info("Validate rendered page");
        SeoData renderedData = new SeoData(renderedDocument, true, testData);
        seoValidationContext.execute(renderedData);

    }
}

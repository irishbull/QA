package ta.test.impl.seo;

import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import io.qameta.allure.Description;
import ta.dataproviders.JSONDataProvider;
import ta.driver.SeleniumDriver;
import ta.test.SeoBaseTest;
import ta.test.impl.seo.validation.SeoData;
import ta.test.impl.seo.validation.SeoValidationContext;
import ta.test.impl.seo.validation.stragety.SeoValidationStrategy;
import ta.test.impl.seo.validation.stragety.impl.HtmlSeoValidationStrategy;
import ta.utilities.BrowserUtils;

import static ta.utilities.constants.Constants.Url.BASE_URL;
import static ta.utilities.constants.ToosoConstants.QUIET_PERIOD;
import static ta.utilities.constants.ToosoConstants.TIMEOUT;


public class ListPageTest extends SeoBaseTest {

    private static final Logger logger = LoggerFactory.getLogger(ListPageTest.class);
    private Document source;
    private Document rendered;

    @Parameters("pagePath")
    @BeforeClass
    public void beforeSuite(String pagePath) throws IOException {
        WebDriver driver = SeleniumDriver.getInstance().getDriver();

        logger.info("ENCODING file.encoding = " + System.getProperty("file.encoding"));
        logger.info("Default Locale:   " + Locale.getDefault());
        logger.info("Default Charset:  " + Charset.defaultCharset());
        logger.info("sun.jnu.encoding: " + System.getProperty("sun.jnu.encoding"));


        String url = BASE_URL + pagePath;
        logger.info("Analyse page with url {}", url);

        driver.get(url);

        // wait for quiescence
        SeleniumDriver.getInstance().getProxy().waitForQuiescence(QUIET_PERIOD, TIMEOUT, TimeUnit.SECONDS);

        // Rendered
        String renderedHtml = BrowserUtils.getRenderedPage();
        rendered = Jsoup.parse(renderedHtml, "UTF-8");

        // Source using Jsoup
        source = Jsoup.connect(url).get();
    }


    @Test(dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class)
    @Description("List page - test <title>")
    public void tc_001_title(JSONObject testData) {

        Set<SeoValidationStrategy> strategies = new LinkedHashSet<>();

        strategies.add(HtmlSeoValidationStrategy.TITLE);

        SeoValidationContext seoValidationContext = new SeoValidationContext(strategies);

        logger.info("Validate source page");
        SeoData sourceData = new SeoData(source, false, testData);
        seoValidationContext.execute(sourceData);

        logger.info("Validate rendered page");
        SeoData renderedData = new SeoData(rendered, true, testData);
        seoValidationContext.execute(renderedData);
    }


    @Test(dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class)
    @Description("List page - test <h1>")
    public void tc_002_header1(JSONObject testData) {

        Set<SeoValidationStrategy> strategies = new LinkedHashSet<>();

        strategies.add(HtmlSeoValidationStrategy.HEADER1);

        SeoValidationContext seoValidationContext = new SeoValidationContext(strategies);

        logger.info("Validate source page");
        SeoData sourceData = new SeoData(source, false, testData);
        seoValidationContext.execute(sourceData);

        logger.info("Validate rendered page");
        SeoData renderedData = new SeoData(rendered, true, testData);
        seoValidationContext.execute(renderedData);
    }


    @Test(dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class)
    @Description("List page - test meta description")
    public void tc_003_metaDescription(JSONObject testData) {

        Set<SeoValidationStrategy> strategies = new LinkedHashSet<>();

        strategies.add(HtmlSeoValidationStrategy.META_DESCRIPTION);

        SeoValidationContext seoValidationContext = new SeoValidationContext(strategies);

        logger.info("Validate source page");
        SeoData sourceData = new SeoData(source, false, testData);
        seoValidationContext.execute(sourceData);

        logger.info("Validate rendered page");
        SeoData renderedData = new SeoData(rendered, true, testData);
        seoValidationContext.execute(renderedData);
    }


    @Test(dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class)
    @Description("List page - test rel canonical")
    public void tc_004_relCanonical(JSONObject testData) {

        Set<SeoValidationStrategy> strategies = new LinkedHashSet<>();

        strategies.add(HtmlSeoValidationStrategy.REL_CANONICAL);

        SeoValidationContext seoValidationContext = new SeoValidationContext(strategies);

        logger.info("Validate source page");
        SeoData sourceData = new SeoData(source, false, testData);
        seoValidationContext.execute(sourceData);

        logger.info("Validate rendered page");
        SeoData renderedData = new SeoData(rendered, true, testData);
        seoValidationContext.execute(renderedData);
    }


    @Test
    @Description("List page - test rel canonical")
    public void tc_004_anchor() {

        Set<SeoValidationStrategy> strategies = new LinkedHashSet<>();

        strategies.add(HtmlSeoValidationStrategy.ANCHOR);

        SeoValidationContext seoValidationContext = new SeoValidationContext(strategies);

        logger.info("Validate source page");
        SeoData sourceData = new SeoData(source, false);
        seoValidationContext.execute(sourceData);

        logger.info("Validate rendered page");
        SeoData renderedData = new SeoData(rendered, true);
        seoValidationContext.execute(renderedData);
    }


    @Test
    @Description("List page - test rel canonical")
    public void tc_004_noindex() {

        Set<SeoValidationStrategy> strategies = new LinkedHashSet<>();

        strategies.add(HtmlSeoValidationStrategy.NOINDEX);

        SeoValidationContext seoValidationContext = new SeoValidationContext(strategies);

        logger.info("Validate source page");
        SeoData sourceData = new SeoData(source, false);
        seoValidationContext.execute(sourceData);

        logger.info("Validate rendered page");
        SeoData renderedData = new SeoData(rendered, true);
        seoValidationContext.execute(renderedData);
    }
}

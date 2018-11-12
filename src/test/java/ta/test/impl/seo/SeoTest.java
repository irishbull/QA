package ta.test.impl.seo;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.LinkedHashSet;
import java.util.Set;

import io.qameta.allure.Description;
import ta.dataproviders.JSONDataProvider;
import ta.test.SeoBaseTest;
import ta.test.impl.seo.validation.SeoData;
import ta.test.impl.seo.validation.SeoValidationContext;
import ta.test.impl.seo.validation.stragety.SeoValidationStrategy;
import ta.test.impl.seo.validation.stragety.impl.HtmlSeoValidationStrategy;

;

public class SeoTest extends SeoBaseTest {

    @Test(dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class)
    @Description("Test <title>")
    public void tc_001_title(JSONObject testData) {

        Set<SeoValidationStrategy> strategies = new LinkedHashSet<>();

        strategies.add(HtmlSeoValidationStrategy.TITLE);

        SeoValidationContext seoValidationContext = new SeoValidationContext(strategies);

        logger.info("Page type[source] - validate[{}]", HtmlSeoValidationStrategy.TITLE);
        SeoData sourceData = new SeoData(source, false, testData);
        seoValidationContext.execute(sourceData);

        logger.info("Page type[rendered] - validate[{}]", HtmlSeoValidationStrategy.TITLE);
        SeoData renderedData = new SeoData(rendered, true, testData);
        seoValidationContext.execute(renderedData);
    }


    @Test(dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class)
    @Description("Test <h1>")
    public void tc_002_header1(JSONObject testData) {

        Set<SeoValidationStrategy> strategies = new LinkedHashSet<>();

        strategies.add(HtmlSeoValidationStrategy.HEADER1);

        SeoValidationContext seoValidationContext = new SeoValidationContext(strategies);

        logger.info("Page type[source] - validate[{}]", HtmlSeoValidationStrategy.HEADER1);
        SeoData sourceData = new SeoData(source, false, testData);
        seoValidationContext.execute(sourceData);

        logger.info("Page type[rendered] - validate[{}]", HtmlSeoValidationStrategy.HEADER1);
        SeoData renderedData = new SeoData(rendered, true, testData);
        seoValidationContext.execute(renderedData);
    }


    @Test(dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class)
    @Description("Test meta description")
    public void tc_003_metaDescription(JSONObject testData) {

        Set<SeoValidationStrategy> strategies = new LinkedHashSet<>();

        strategies.add(HtmlSeoValidationStrategy.META_DESCRIPTION);

        SeoValidationContext seoValidationContext = new SeoValidationContext(strategies);

        logger.info("Page type[source] - validate[{}]", HtmlSeoValidationStrategy.META_DESCRIPTION);
        SeoData sourceData = new SeoData(source, false, testData);
        seoValidationContext.execute(sourceData);

        logger.info("Page type[rendered] - validate[{}]", HtmlSeoValidationStrategy.META_DESCRIPTION);
        SeoData renderedData = new SeoData(rendered, true, testData);
        seoValidationContext.execute(renderedData);
    }


    @Test(dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class)
    @Description("Test rel canonical")
    public void tc_004_relCanonical(JSONObject testData) {

        Set<SeoValidationStrategy> strategies = new LinkedHashSet<>();

        strategies.add(HtmlSeoValidationStrategy.REL_CANONICAL);

        SeoValidationContext seoValidationContext = new SeoValidationContext(strategies);

        logger.info("Page type[source] - validate[{}]", HtmlSeoValidationStrategy.REL_CANONICAL );
        SeoData sourceData = new SeoData(source, false, testData);
        seoValidationContext.execute(sourceData);

        logger.info("Page type[rendered] - validate[{}]", HtmlSeoValidationStrategy.REL_CANONICAL);
        SeoData renderedData = new SeoData(rendered, true, testData);
        seoValidationContext.execute(renderedData);
    }


    @Test
    @Description("Test rel canonical")
    public void tc_004_anchor() {

        Set<SeoValidationStrategy> strategies = new LinkedHashSet<>();

        strategies.add(HtmlSeoValidationStrategy.ANCHOR);

        SeoValidationContext seoValidationContext = new SeoValidationContext(strategies);

        logger.info("Page type[source] - validate[{}]", HtmlSeoValidationStrategy.ANCHOR);
        SeoData sourceData = new SeoData(source, false);
        seoValidationContext.execute(sourceData);

        logger.info("Page type[rendered] - validate[{}]", HtmlSeoValidationStrategy.ANCHOR);
        SeoData renderedData = new SeoData(rendered, true);
        seoValidationContext.execute(renderedData);
    }


    @Test
    @Description("Test rel canonical")
    public void tc_004_noindex() {

        Set<SeoValidationStrategy> strategies = new LinkedHashSet<>();

        strategies.add(HtmlSeoValidationStrategy.NOINDEX);

        SeoValidationContext seoValidationContext = new SeoValidationContext(strategies);

        logger.info("Page type[source] - validate[{}]", HtmlSeoValidationStrategy.NOINDEX );
        SeoData sourceData = new SeoData(source, false);
        seoValidationContext.execute(sourceData);

        logger.info("Page type[rendered] - validate[{}]", HtmlSeoValidationStrategy.NOINDEX);
        SeoData renderedData = new SeoData(rendered, true);
        seoValidationContext.execute(renderedData);
    }
}

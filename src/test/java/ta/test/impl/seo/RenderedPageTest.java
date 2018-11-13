package ta.test.impl.seo;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.LinkedHashSet;
import java.util.Set;

import io.qameta.allure.Description;
import ta.dataproviders.JSONDataProvider;
import ta.test.SeoRenderedTest;
import ta.test.impl.seo.validation.SeoData;
import ta.test.impl.seo.validation.SeoValidationContext;
import ta.test.impl.seo.validation.stragety.SeoValidationStrategy;
import ta.test.impl.seo.validation.stragety.impl.HtmlSeoValidationStrategy;

;

public class RenderedPageTest extends SeoRenderedTest {

    @Test(dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class)
    @Description("<p><code>&lt;title&gt;</code></p><ul><li>esiste</li><li>il suo valore è uguale a quello atteso</li></ul>")
    public void tc_001_title(JSONObject testData) {

        Set<SeoValidationStrategy> strategies = new LinkedHashSet<>();

        strategies.add(HtmlSeoValidationStrategy.TITLE);

        SeoValidationContext seoValidationContext = new SeoValidationContext(strategies);

        logger.info("Page type[rendered] - validate[{}]", HtmlSeoValidationStrategy.TITLE);
        SeoData renderedData = new SeoData(rendered, true, testData);
        seoValidationContext.execute(renderedData);
    }


    @Test(dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class)
    @Description("<p><code>&lt;h1&gt;</code></p><ul><li>esiste ed è unico</li><li>il suo valore è uguale a quello atteso</li></ul>")
    public void tc_002_header1(JSONObject testData) {

        Set<SeoValidationStrategy> strategies = new LinkedHashSet<>();

        strategies.add(HtmlSeoValidationStrategy.HEADER1);

        SeoValidationContext seoValidationContext = new SeoValidationContext(strategies);

        logger.info("Page type[rendered] - validate[{}]", HtmlSeoValidationStrategy.HEADER1);
        SeoData renderedData = new SeoData(rendered, true, testData);
        seoValidationContext.execute(renderedData);
    }


    @Test(dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class)
    @Description("<p><code>&lt;meta name=&#8220;description&#8221; content=&#8220;...&#8221;&gt;</code></p><ul><li>esiste ed è unico</li><li>il valore di <code>href</code> è uguale allo URL della pagina testata</li></ul>")
    public void tc_003_metaDescription(JSONObject testData) {

        Set<SeoValidationStrategy> strategies = new LinkedHashSet<>();

        strategies.add(HtmlSeoValidationStrategy.META_DESCRIPTION);

        SeoValidationContext seoValidationContext = new SeoValidationContext(strategies);

        logger.info("Page type[rendered] - validate[{}]", HtmlSeoValidationStrategy.META_DESCRIPTION);
        SeoData renderedData = new SeoData(rendered, true, testData);
        seoValidationContext.execute(renderedData);
    }


    @Test(dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class)
    @Description("<p><code>&lt;link rel=&#8220;canonical&#8221; href=&#8220;...&#8221;&gt;</code></p><ul><li>esiste ed è unico</li><li>il valore di <code>href</code> è uguale a quello atteso</li></ul>")
    public void tc_004_relCanonical(JSONObject testData) {

        Set<SeoValidationStrategy> strategies = new LinkedHashSet<>();

        strategies.add(HtmlSeoValidationStrategy.REL_CANONICAL);

        SeoValidationContext seoValidationContext = new SeoValidationContext(strategies);

        logger.info("Page type[rendered] - validate[{}]", HtmlSeoValidationStrategy.REL_CANONICAL);
        SeoData renderedData = new SeoData(rendered, true, testData);
        seoValidationContext.execute(renderedData);
    }


    @Test(dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class)
    @Description("<p><code>&lt;a href=&#8220;...&#8221</code></p><ul><li>il valore di <code>href</code> non deve contenere <code>/undefined</code></li><li>il valore di <code>href</code> non deve iniziare con <code>/http</code></li></ul>")
    public void tc_005_anchor(JSONObject testData) {

        Set<SeoValidationStrategy> strategies = new LinkedHashSet<>();

        strategies.add(HtmlSeoValidationStrategy.IMAGE);

        SeoValidationContext seoValidationContext = new SeoValidationContext(strategies);

        logger.info("Page type[rendered] - validate[{}]", HtmlSeoValidationStrategy.IMAGE);
        SeoData renderedData = new SeoData(rendered, true, testData);
        seoValidationContext.execute(renderedData);
    }


    @Test(dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class)
    @Description("<p><code>&lt;index&gt;</code> e <code>&lt;meta ... content=&#8220;noindex&#8221&gt;</code></p><ul><li>non esistono nella pagina corrente</li></ul>")
    public void tc_006_noindex(JSONObject testData) {

        Set<SeoValidationStrategy> strategies = new LinkedHashSet<>();

        strategies.add(HtmlSeoValidationStrategy.NOINDEX);

        SeoValidationContext seoValidationContext = new SeoValidationContext(strategies);

        logger.info("Page type[rendered] - validate[{}]", HtmlSeoValidationStrategy.NOINDEX);
        SeoData renderedData = new SeoData(rendered, true, testData);
        seoValidationContext.execute(renderedData);
    }
}

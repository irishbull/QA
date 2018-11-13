package ta.test.impl.seo;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.LinkedHashSet;
import java.util.Set;

import io.qameta.allure.Description;
import ta.dataproviders.JSONDataProvider;
import ta.test.SeoSourceTest;
import ta.test.impl.seo.validation.SeoData;
import ta.test.impl.seo.validation.SeoValidationContext;
import ta.test.impl.seo.validation.stragety.SeoValidationStrategy;
import ta.test.impl.seo.validation.stragety.impl.HtmlSeoValidationStrategy;

;

public class SourcePageTest extends SeoSourceTest {

    @Test(dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class)
    @Description("<p><code>&lt;title&gt;</code></p><ul><li>esiste</li><li>il suo valore è uguale a quello atteso</li></ul>")
    public void tc_001_title(JSONObject testData) {

        Set<SeoValidationStrategy> strategies = new LinkedHashSet<>();

        strategies.add(HtmlSeoValidationStrategy.TITLE);

        SeoValidationContext seoValidationContext = new SeoValidationContext(strategies);

        logger.info("Page type[source] - validate[{}]", HtmlSeoValidationStrategy.TITLE);
        SeoData sourceData = new SeoData(source, false, testData);
        seoValidationContext.execute(sourceData);
    }


    @Test(dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class)
    @Description("<p><code>&lt;h1&gt;</code></p><ul><li>esiste ed è unico</li><li>il suo valore è uguale a quello atteso</li></ul>")
    public void tc_002_header1(JSONObject testData) {

        Set<SeoValidationStrategy> strategies = new LinkedHashSet<>();

        strategies.add(HtmlSeoValidationStrategy.HEADER1);

        SeoValidationContext seoValidationContext = new SeoValidationContext(strategies);

        logger.info("Page type[source] - validate[{}]", HtmlSeoValidationStrategy.HEADER1);
        SeoData sourceData = new SeoData(source, false, testData);
        seoValidationContext.execute(sourceData);
    }


    @Test(dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class)
    @Description("<p><code>&lt;meta name=&#8220;description&#8221; content=&#8220;...&#8221;&gt;</code></p><ul><li>esiste ed è unico</li><li>il valore di <code>href</code> è uguale allo URL della pagina testata</li></ul>")
    public void tc_003_metaDescription(JSONObject testData) {

        Set<SeoValidationStrategy> strategies = new LinkedHashSet<>();

        strategies.add(HtmlSeoValidationStrategy.META_DESCRIPTION);

        SeoValidationContext seoValidationContext = new SeoValidationContext(strategies);

        logger.info("Page type[source] - validate[{}]", HtmlSeoValidationStrategy.META_DESCRIPTION);
        SeoData sourceData = new SeoData(source, false, testData);
        seoValidationContext.execute(sourceData);
    }


    @Test(dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class)
    @Description("<p><code>&lt;link rel=&#8220;canonical&#8221; href=&#8220;...&#8221;&gt;</code></p><ul><li>esiste ed è unico</li><li>il valore di <code>href</code> è uguale a quello atteso</li></ul>")
    public void tc_004_relCanonical(JSONObject testData) {

        Set<SeoValidationStrategy> strategies = new LinkedHashSet<>();

        strategies.add(HtmlSeoValidationStrategy.REL_CANONICAL);

        SeoValidationContext seoValidationContext = new SeoValidationContext(strategies);

        logger.info("Page type[source] - validate[{}]", HtmlSeoValidationStrategy.REL_CANONICAL);
        SeoData sourceData = new SeoData(source, false, testData);
        seoValidationContext.execute(sourceData);
    }


    @Test
    @Description("<p><code>&lt;a href=&#8220;...&#8221</code></p><ul><li>il valore di <code>href</code> non deve contenere <code>/undefined</code></li><li>il valore di <code>href</code> non deve iniziare con <code>/http</code></li></ul>")
    public void tc_005_anchor() {

        Set<SeoValidationStrategy> strategies = new LinkedHashSet<>();

        strategies.add(HtmlSeoValidationStrategy.ANCHOR);

        SeoValidationContext seoValidationContext = new SeoValidationContext(strategies);

        logger.info("Page type[source] - validate[{}]", HtmlSeoValidationStrategy.ANCHOR);
        SeoData sourceData = new SeoData(source, false);
        seoValidationContext.execute(sourceData);
    }


    @Test
    @Description("<p><code>&lt;index&gt;</code> e <code>&lt;meta ... content=&#8220;noindex&#8221&gt;</code></p><ul><li>non esistono nella pagina corrente</li></ul>")
    public void tc_006_noindex() {

        Set<SeoValidationStrategy> strategies = new LinkedHashSet<>();

        strategies.add(HtmlSeoValidationStrategy.NOINDEX);

        SeoValidationContext seoValidationContext = new SeoValidationContext(strategies);

        logger.info("Page type[source] - validate[{}]", HtmlSeoValidationStrategy.NOINDEX);
        SeoData sourceData = new SeoData(source, false);
        seoValidationContext.execute(sourceData);
    }
}

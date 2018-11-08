package ta.test.impl.seo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.qameta.allure.Description;
import ta.driver.SeleniumDriver;
import ta.test.ToosoBaseTest;
import ta.utilities.JavascriptUtils;

import static ta.utilities.constants.Constants.Url.BASE_URL;
import static ta.utilities.constants.ToosoConstants.QUIET_PERIOD;
import static ta.utilities.constants.ToosoConstants.TIMEOUT;

public class OldSeoTest extends ToosoBaseTest {

    private static final Logger logger = LoggerFactory.getLogger(OldSeoTest.class);

    private static final String DEFAULT_METADESCRIPTION = "Scopri tutti i prodotti e le offerte Leroy Merlin per l'arredo della tua casa e il bricolage. Un vastissimo catalogo da acquistare online o in negozio!";

    @Test
    @Description("SEO test")
    public void tc_001() throws IOException {

        logger.info("SEO test");

        WebDriver driver = SeleniumDriver.getInstance().getDriver();

        //TODO retrieve url from json file
        String url = BASE_URL + "/catalogo/porta-doccia-battente-elba-68-72-h-185-cm-cristallo-3-mm-piumato-bianco-lucido-30556932-p";

        logger.info("Analyze page with url {}", url);

        driver.get(url);

        // wait for quiescence
        SeleniumDriver.getInstance().getProxy().waitForQuiescence(QUIET_PERIOD, TIMEOUT, TimeUnit.SECONDS);

        //RENDERED
        String renderedHtml = JavascriptUtils.execute("return document.getElementsByTagName('html')[0].innerHTML").toString();
        Document renderedDocument = Jsoup.parse(renderedHtml);

        // SOURCE using Jsoup
        Document sourceDocument = Jsoup.connect(url).get();

        logger.info("Validate source document");
        validate(sourceDocument, url);

        logger.info("Validate rendered document");
        validate(renderedDocument, url);


        /*
        // SOURCE using proxy
        Har har = SeleniumDriver.getInstance().getProxy().getHar();
        List<HarEntry> entriesCaptured = har.getLog().getEntries();

        // TODO filter
        HarEntry entry = entriesCaptured.stream().filter(x -> x.getRequest().getUrl().equalsIgnoreCase(url)).findAny()
                .orElse(null);

        Assert.assertNotNull(entry);

        String htmlBody = entry.getResponse().getContent().getText();

        Document doc = Jsoup.parse(htmlBody);

        Elements links = doc.getElementsByTag("a");

        for (Element link : links) {
            String linkHref = link.attr("href");
            logger.info("href to validate: {}", linkHref);
            Assert.assertFalse(linkHref.contains("undefined"), "href contains undefined value");
        }
        */

    }


    private static void validate(Document document, String url) {

        String title = document.title();
        logger.info("Title = {}", title);
        Assert.assertNotEquals("", title, "Title should not be empty");
        Assert.assertNotEquals("Leroy Merlin", title.trim(), "Title should not be equal to Leroy Merlin");


        // Heading 1
        Elements headings1 = document.getElementsByTag("h1");
        Assert.assertEquals(headings1.size(), 1, "Heading 1 should be unique");
        logger.info("Heading text = {}", headings1.text());
        //TODO validation


        // Meta description
        Elements metaDescriptions = document.select("meta[name=\"description\"]");
        Assert.assertEquals(metaDescriptions.size(), 1, "Meta description should be unique");
        String metaDescription =  metaDescriptions.get(0).attr("content");
        logger.info("Meta description = {}", metaDescription);
        Assert.assertNotEquals(metaDescription, DEFAULT_METADESCRIPTION);


        // Canonical
        Elements canonicals = document.select("link[rel=\"canonical\"]");
        for(Element canonical : canonicals) {
            logger.info("Canonical = {}", canonical);
            Assert.assertEquals(url, canonical.attr("href"),"Canonical href value should be equal to URL");
        }


        // Anchor href
        Elements anchors = document.getElementsByTag("a");
        for(Element anchor : anchors) {
            String href = anchor.attr("href");
            logger.info("Anchor href value = {}", href);
            Assert.assertFalse(href.contains("undefined"), "href contains undefined value");
            Assert.assertFalse(href.startsWith("/"), "href value starts with /");
        }

    }
}

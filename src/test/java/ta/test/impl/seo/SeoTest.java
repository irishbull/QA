package ta.test.impl.seo;

import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.qameta.allure.Description;
import ta.driver.SeleniumDriver;
import ta.test.ToosoBaseTest;

import static ta.utilities.constants.Constants.Url.BASE_URL;
import static ta.utilities.constants.ToosoConstants.QUIET_PERIOD;
import static ta.utilities.constants.ToosoConstants.TIMEOUT;

public class SeoTest extends ToosoBaseTest {

    private static final Logger logger = LoggerFactory.getLogger(SeoTest.class);

    @Test
    @Description("SEO test")
    public void tc_001() {

        logger.info("SEO test");

        WebDriver driver = SeleniumDriver.getInstance().getDriver();

        //TODO retrieve url from json file
        String url = BASE_URL + "/catalogo/porta-doccia-battente-elba-68-72-h-185-cm-cristallo-3-mm-piumato-bianco-lucido-30556932-p";

        driver.get(url);

        // wait for quiescence
        SeleniumDriver.getInstance().getProxy().waitForQuiescence(QUIET_PERIOD, TIMEOUT, TimeUnit.SECONDS);

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

        logger.info("Title : " + doc.title());
    }
}

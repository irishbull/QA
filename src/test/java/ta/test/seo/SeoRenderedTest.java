package ta.test.seo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

import ta.driver.SeleniumDriver;
import ta.utilities.BrowserUtils;

import static ta.utilities.constants.Constants.Url.BASE_URL;
import static ta.utilities.constants.ToosoConstants.QUIET_PERIOD;
import static ta.utilities.constants.ToosoConstants.TIMEOUT;


public abstract class SeoRenderedTest extends SeoBaseTest {

    protected Document rendered;

    @Parameters("pagePath")
    @BeforeClass
    public void getRenderedPage(String pagePath) {

        Assert.assertNotNull(pagePath, "The parameter 'pagePath' is mandatory. It cannot be null");

        String url = BASE_URL + pagePath;
        logger.info("Analyse rendered page with url {}", url);

        // Rendered page
        // wait for quiescence
        SeleniumDriver.getInstance().getProxy().waitForQuiescence(QUIET_PERIOD, TIMEOUT, TimeUnit.SECONDS);
        String renderedHtml = BrowserUtils.getRenderedPage();
        rendered = Jsoup.parse(renderedHtml, "UTF-8");
    }
}

package ta.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import ta.driver.SeleniumDriver;
import ta.utilities.BrowserUtils;

import static ta.utilities.constants.Constants.Url.BASE_URL;
import static ta.utilities.constants.ToosoConstants.QUIET_PERIOD;
import static ta.utilities.constants.ToosoConstants.TIMEOUT;


public abstract class SeoBaseTest extends BaseTest {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected Document source;
    protected Document rendered;

    @Parameters("pagePath")
    @BeforeClass
    public void getSourceAndRenderedPage(String pagePath) throws IOException {
        WebDriver driver = SeleniumDriver.getInstance().getDriver();

        logger.info("ENCODING file.encoding = " + System.getProperty("file.encoding"));
        logger.info("Default Locale:   " + Locale.getDefault());
        logger.info("Default Charset:  " + Charset.defaultCharset());
        logger.info("sun.jnu.encoding: " + System.getProperty("sun.jnu.encoding"));

        String url = BASE_URL + pagePath;
        logger.info("Analyse page with url {}", url);

        driver.get(url);

        // Rendered page
        // wait for quiescence
        SeleniumDriver.getInstance().getProxy().waitForQuiescence(QUIET_PERIOD, TIMEOUT, TimeUnit.SECONDS);
        String renderedHtml = BrowserUtils.getRenderedPage();
        rendered = Jsoup.parse(renderedHtml, "UTF-8");

        // Source page
        // Disable TLS certificates validation for HTTPS requests.
        // Note that this timeout specifies the combined maximum duration of the connection time and the time to read the full response
        source = Jsoup.connect(url).timeout(10_000).validateTLSCertificates(false).get();
    }
}

package ta.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.IOException;

import ta.driver.SeleniumDriver;

import static ta.utilities.constants.Constants.Url.BASE_URL;


public abstract class SeoSourceTest extends SeoBaseTest {

    protected Document source;

    @Parameters("pagePath")
    @BeforeClass
    public void getSourcePage(String pagePath) throws IOException {

        Assert.assertNotNull(pagePath, "The parameter 'pagePath' is mandatory. It cannot be null");

        WebDriver driver = SeleniumDriver.getInstance().getDriver();

        String url = BASE_URL + pagePath;
        logger.info("Analyse source page with url {}", url);

        driver.get(url);

        // Source page
        // Disable TLS certificates validation for HTTPS requests.
        // Note that this timeout specifies the combined maximum duration of the connection time and the time to read the full response
        source = Jsoup.connect(url).timeout(10_000).validateTLSCertificates(false).get();
    }
}

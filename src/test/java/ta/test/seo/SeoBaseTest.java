package ta.test.seo;

import org.testng.annotations.BeforeSuite;

import java.nio.charset.Charset;
import java.util.Locale;

import ta.test.BaseTest;


public abstract class SeoBaseTest extends BaseTest {

    @BeforeSuite
    public void encodeInfo() {
        logger.info("Encoding");
        logger.info("file.encoding = {}" + System.getProperty("file.encoding"));
        logger.info("default locale = {}", Locale.getDefault());
        logger.info("default charset = {}", Charset.defaultCharset());
        logger.info("sun.jnu.encoding = {}", System.getProperty("sun.jnu.encoding"));
    }
}

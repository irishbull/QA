package ta.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeSuite;

import java.nio.charset.Charset;
import java.util.Locale;


public abstract class SeoBaseTest extends BaseTest {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @BeforeSuite
    public void encodeInfo() {
        logger.info("Encoding");
        logger.info("file.encoding = {}" + System.getProperty("file.encoding"));
        logger.info("default locale = {}", Locale.getDefault());
        logger.info("default charset = {}", Charset.defaultCharset());
        logger.info("sun.jnu.encoding = {}", System.getProperty("sun.jnu.encoding"));
    }
}

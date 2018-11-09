package ta.test;

import net.lightbody.bmp.core.har.Har;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.lang.reflect.Method;
import java.util.Objects;

import ta.driver.SeleniumDriver;


public abstract class SeoBaseTest extends BaseTest {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @BeforeTest
    public void createHar() {
        SeleniumDriver.getInstance().getProxy().newHar(this.getClass().getName());
        logger.info("New har {} created for test {}", SeleniumDriver.getInstance().getProxy().getHar(), this.getClass().getName());
    }

    @AfterTest
    public void endHar() {

        Har har = SeleniumDriver.getInstance().getProxy().getHar();

        if (Objects.nonNull(har)) {
            SeleniumDriver.getInstance().getProxy().endHar();
            logger.info("Har {} closed for test {}", har, this.getClass().getName());
        }
    }
}

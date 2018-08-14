package ta.test;

import net.lightbody.bmp.core.har.Har;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;
import java.util.Objects;

import ta.driver.SeleniumDriver;


public abstract class ToosoBaseTest extends BaseTest {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @BeforeMethod
    public void createHar(Method method) {
        SeleniumDriver.getInstance().getProxy().newHar(method.getName());
        logger.info("New har {} created for method {}", SeleniumDriver.getInstance().getProxy().getHar(), method.getName());
    }

    @AfterMethod
    public void endHar(Method method) {

        Har har = SeleniumDriver.getInstance().getProxy().getHar();

        if (Objects.nonNull(har)) {
            SeleniumDriver.getInstance().getProxy().endHar();
            logger.info("Har {} closed for method {}", har, method.getName());
        }
    }
}

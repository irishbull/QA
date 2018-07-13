package ta.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.Objects;

import ta.driver.SeleniumDriver;
import ta.utilities.ReadPropertiesFile;


public abstract class BaseTest {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @BeforeTest
    public void testSetup() {
        logger.info("Set up");

        String environment = ReadPropertiesFile.getProperty("environment");
        Assert.assertNotNull(environment, "Required property 'environment' not found");
        logger.info("Environmente = [{}]", environment);

        String browser = System.getProperty("browser");
        Assert.assertNotNull(browser, "Required parameter 'Browser' is missing");

        /* debug locally
        String browser = System.getProperty("browser", "chrome");
        logger.info("Browser [{}]", browser);
        */
        SeleniumDriver.getInstance().setDriver(browser);
        logger.info("Driver instance {}", SeleniumDriver.getInstance().toString());

        SeleniumDriver.getInstance().getDriver().manage().window().maximize();
    }

    @AfterTest
    public void suiteTeardown() {
        logger.info("Tear down");
        logger.info("Driver instance {}", SeleniumDriver.getInstance().toString());
        SeleniumDriver.getInstance().getDriver().manage().deleteAllCookies();
        SeleniumDriver.getInstance().getDriver().quit();
    }
}

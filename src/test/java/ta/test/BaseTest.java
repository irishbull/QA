package ta.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;

import ta.driver.SeleniumDriver;
import ta.utilities.ReadPropertiesFile;


public abstract class BaseTest {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private static final String FILE_PATH = "C:\\Users\\bolognagi\\Desktop\\har\\result_";

    @Parameters("isProxyRequired")
    @BeforeSuite
    public void beforeSuite(@Optional("false") boolean isProxyRequired) {
        logger.info("Suite set up");

        // TO REMOVE
        String environment = ReadPropertiesFile.getProperty("environment");
        Assert.assertNotNull(environment, "Required property 'environment' not found");
        logger.info("Environment = [{}]", environment);

        String browser = System.getProperty("browser");
        Assert.assertNotNull(browser, "Required parameter 'Browser' is missing");

        /* debug locally
        String browser = System.getProperty("browser", "chrome");
        logger.info("Browser [{}]", browser);
        */

        SeleniumDriver.getInstance().setDriver(browser, isProxyRequired);
        logger.info("Driver instance {}", SeleniumDriver.getInstance().toString());

        SeleniumDriver.getInstance().getDriver().manage().window().maximize();
    }


    @Parameters("isProxyRequired")
    @AfterSuite
    public void afterSuite(@Optional("false") boolean isProxyRequired) {
        logger.info("Suite tear down");
        logger.info("Driver instance {}", SeleniumDriver.getInstance().toString());

        if(isProxyRequired && Objects.nonNull(SeleniumDriver.getInstance().getProxy())) {
            SeleniumDriver.getInstance().getProxy().stop();
            logger.info("Proxy stopped");
        }

        //TO REMOVE
        File harFile = new File(FILE_PATH + new Date().getTime() + ".har");
        try {
            SeleniumDriver.getInstance().getProxy().getHar().writeTo(harFile);
        } catch (IOException ex) {
            System.out.println (ex.toString());
            System.out.println("Could not find file " + FILE_PATH);
        }

        SeleniumDriver.getInstance().getDriver().manage().deleteAllCookies();
        SeleniumDriver.getInstance().getDriver().quit();
    }
}

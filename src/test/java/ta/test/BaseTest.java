package ta.test;

import org.openqa.selenium.Dimension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.Objects;

import ta.driver.SeleniumDriver;
import ta.utilities.CookiesUtils;
import ta.utilities.LocalStorage;
import ta.utilities.ReadPropertiesFile;

import static ta.utilities.constants.Constants.LocalStorage.ACCEPT;
import static ta.utilities.constants.Constants.LocalStorage.CURRENT_CUSTOMER_STORE;
import static ta.utilities.constants.Constants.LocalStorage.CURRENT_CUSTOMER_STORE_VALUE;
import static ta.utilities.constants.Constants.LocalStorage.STORE_CONSENT;
import static ta.utilities.constants.Constants.Url.BASE_URL;


public abstract class BaseTest {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Parameters("isProxyRequired")
    @BeforeSuite
    public void beforeSuite(@Optional("false") boolean isProxyRequired) {
        logger.info("Suite set up");

        Assert.assertNotNull(System.getProperty("targetEnv"), "TARGET ENVIRONMENT not found");
        logger.info("Target environment = [{}]", System.getProperty("targetEnv"));

        // TODO REMOVE
        String environment = ReadPropertiesFile.getProperty("environment");
        Assert.assertNotNull(environment, "Required property 'environment' not found");
        logger.info("Environment value specified in environment property file is : [{}]", environment);

        String browser = System.getProperty("selectedBrowser");
        Assert.assertNotNull(browser, "Required parameter 'Browser' is missing");

        SeleniumDriver.getInstance().setDriver(browser, isProxyRequired);
        logger.info("Driver instance {}", SeleniumDriver.getInstance().toString());

        SeleniumDriver.getInstance().getDriver().manage().window().setSize(new Dimension(1920, 1080));

        // set current customer store
        SeleniumDriver.getInstance().getDriver().get(BASE_URL);
        CookiesUtils.addCurrentCustomerStore();
        LocalStorage.setItem(CURRENT_CUSTOMER_STORE, CURRENT_CUSTOMER_STORE_VALUE);
        LocalStorage.setItem(STORE_CONSENT, ACCEPT);
    }


    @Parameters("isProxyRequired")
    @AfterSuite
    public void afterSuite(@Optional("false") boolean isProxyRequired) {
        logger.info("Suite tear down");
        logger.info("Driver instance {}", SeleniumDriver.getInstance().toString());

        if (isProxyRequired && Objects.nonNull(SeleniumDriver.getInstance().getProxy())) {
            SeleniumDriver.getInstance().getProxy().stop();
            logger.info("Proxy stopped");
        }

        SeleniumDriver.getInstance().getDriver().manage().deleteAllCookies();
        LocalStorage.clear();

        SeleniumDriver.getInstance().getDriver().quit();
    }
}

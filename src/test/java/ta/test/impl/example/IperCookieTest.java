package ta.test.impl.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import ta.driver.SeleniumDriver;
import ta.test.BaseTest;
import ta.utilities.JavascriptUtils;

// Test of www.iperdrive.it site to manage cookies modal

public class IperCookieTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(IperCookieTest.class);

    @Test
    public void cookieTest() throws Exception {

        logger.info("thread-id:{}", String.valueOf(Thread.currentThread().getId()));

        WebDriver driver = SeleniumDriver.getInstance().getDriver();

        driver.get("http://www.iperdrive.it/");

        // Because Selenium driver cannot interact with modal, we need to use
        // JavascriptExecutor to execute native javascript code
        JavascriptUtils.modalClose(By.className("remodal-close"));

    }
}

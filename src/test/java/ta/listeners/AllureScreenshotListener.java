package ta.listeners;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Objects;

import io.qameta.allure.Allure;
import ta.driver.SeleniumDriver;

public class AllureScreenshotListener implements IInvokedMethodListener {

    private static final Logger logger = LoggerFactory.getLogger(AllureScreenshotListener.class);

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        // blank override
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod() && !testResult.isSuccess()) {
            attachScreenshot();
        }
    }

    private void attachScreenshot() {

        try {

            // do not attach screenshot to Tooso/Analytics test results (proxy is started)
            if (Objects.nonNull(SeleniumDriver.getInstance().getProxy()) && SeleniumDriver.getInstance().getProxy().isStarted()) {
                return;
            }

            WebDriver driver = SeleniumDriver.getInstance().getDriver();

            Allure
                    .addAttachment("Screenshot", new ByteArrayInputStream(FileUtils
                            .readFileToByteArray(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE))));

        } catch (IOException e) {
            logger.error("Error executing attachScreenshot", e);
        }

    }

}

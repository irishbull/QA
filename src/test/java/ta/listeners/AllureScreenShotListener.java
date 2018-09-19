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

public class AllureScreenShotListener implements IInvokedMethodListener {

    private static final Logger logger = LoggerFactory.getLogger(AllureScreenShotListener.class);

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        // blank override
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {

        // Attach screenshot in case of failure only if the proxy is off
        if (method.isTestMethod() && !testResult.isSuccess() && isScreenShotRequired(method)) {

            attachScreenShot();
        }
    }


    private boolean isScreenShotRequired(IInvokedMethod method) {
        // Screen-shot is required when proxy is NOT required (suite parameter isProxyRequire = false or missing)
        boolean isProxyRequired = Objects.isNull(method.getTestMethod().getXmlTest().getSuite().getParameter("isProxyRequired")) ?
                false : Boolean.valueOf(method.getTestMethod().getXmlTest().getSuite().getParameter("isProxyRequired"));

        return !isProxyRequired;
    }


    private void attachScreenShot() {

        try {

            WebDriver driver = SeleniumDriver.getInstance().getDriver();

            Allure
                    .addAttachment("Screenshot", new ByteArrayInputStream(FileUtils
                            .readFileToByteArray(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE))));

        } catch (IOException e) {
            logger.error("Error executing attachScreenShot", e);
        }

    }

}

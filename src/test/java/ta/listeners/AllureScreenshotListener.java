package ta.listeners;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import io.qameta.allure.Allure;
import ta.driver.SeleniumDriver;

public class AllureScreenshotListener implements IInvokedMethodListener {

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod() && !testResult.isSuccess()) {
            attachScreenshot();
        }
    }

    private void attachScreenshot() {

        try {

            WebDriver driver = SeleniumDriver.getInstance().getDriver();
            Allure
                    .addAttachment("Screenshot", new ByteArrayInputStream(FileUtils
                            .readFileToByteArray(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE))));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

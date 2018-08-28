package ta.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import ta.driver.SeleniumDriver;

/*
 * Javascript utilities class
 */
public class JavascriptUtils {

    /**
     * Check if a page is completely rendered
     *
     * @return
     */
    public static boolean isPageReady() {
        JavascriptExecutor js = (JavascriptExecutor) SeleniumDriver.getInstance().getDriver();
        return (boolean) js.executeScript("return document.readyState").equals("complete");
    }


    /**
     * Execute a js command
     *
     * @param command
     * @return
     */
    public static Object execute(String command) {

        JavascriptExecutor js = (JavascriptExecutor) SeleniumDriver.getInstance().getDriver();
        return js.executeScript(command);
    }


    /**
     * Execute a parameterized js command on WebElement
     *
     * @param command
     * @param element
     */
    public static void execute(String command, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) SeleniumDriver.getInstance().getDriver();
        js.executeScript(command, element);
    }


    /**
     * Execute a js click event
     *
     * @param element
     */
    public static void click(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) SeleniumDriver.getInstance().getDriver();
        js.executeScript("arguments[0].click();", element);
    }


    /**
     * Close modal
     *
     * @param by
     */
    public static void modalClose(By by) {
        WebDriver driver = SeleniumDriver.getInstance().getDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement elementToClick = driver.findElement(by);
        js.executeScript("arguments[0].click();", elementToClick);
    }


    private JavascriptUtils() {
        throw new IllegalStateException("JavascriptUtils - Object construction is forbidden");
    }
}

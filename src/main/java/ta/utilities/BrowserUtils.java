package ta.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ta.driver.SeleniumDriver;

/*
 * Browser utilities class
 */
public class BrowserUtils {

    private static final String CONSTRUCTION_FORBIDDEN = "BrowserUtils - Object construction is forbidden";

    /**
     * wait for page fully loaded
     */
    public static void waitForPageFullyLoaded(int timeout) throws Exception {
        WebDriver driver = SeleniumDriver.getInstance().getDriver();
        ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState")
                        .equals("complete");
            }
        };
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(pageLoadCondition);
    }


    /**
     * wait up before throwing exception (static locator)
     */
    public static void waitForClickable(WebElement element, int timer) throws Exception {
        WebDriver driver = SeleniumDriver.getInstance().getDriver();

        // wait for the static element to appear
        WebDriverWait wait = new WebDriverWait(driver, timer);

        wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(element)));
    }


    /**
     * wait up before throwing exception (static locator)
     */
    public static void waitFor(WebElement element, int timer) throws Exception {
        WebDriver driver = SeleniumDriver.getInstance().getDriver();

        // wait for the static element to appear
        WebDriverWait wait = new WebDriverWait(driver, timer);

        wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));
    }


    /**
     * wait up before throwing exception (dynamic locator)
     */
    public static void waitForClickable(By by, int timer) throws Exception {
        WebDriver driver = SeleniumDriver.getInstance().getDriver();

        // wait for the dynamic element to appear
        WebDriverWait wait = new WebDriverWait(driver, timer);

        wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(by)));
    }


    /**
     * wait up before throwing exception (dynamic locator)
     */
    public static void waitFor(By by, int timer) throws Exception {
        WebDriver driver = SeleniumDriver.getInstance().getDriver();

        // wait for the dynamic element to appear
        WebDriverWait wait = new WebDriverWait(driver, timer);

        wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(by)));
    }


    /**
     * waitFor method to poll page title
     */
    public static void waitForTitle(String title, int timer) throws Exception {

        WebDriverWait wait = new WebDriverWait(SeleniumDriver.getInstance().getDriver(), timer);
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.titleContains(title)));
    }


    /**
     * waitForURLContains method to poll page URL
     */
    public static void waitForURLContains(String url, int timer) throws Exception {

        WebDriver driver = SeleniumDriver.getInstance().getDriver();
        WebDriverWait wait = new WebDriverWait(driver, timer);

        wait.until(ExpectedConditions.refreshed(ExpectedConditions.urlContains(url)));
    }


    /**
     * waitForURLMatches method to poll page that matches the specified URL
     */
    public static void waitForURLMatches(String url, int timer) throws Exception {

        WebDriver driver = SeleniumDriver.getInstance().getDriver();
        WebDriverWait wait = new WebDriverWait(driver, timer);

        wait.until(ExpectedConditions.refreshed(ExpectedConditions.urlMatches(url)));
    }


    /**
     * check if a webElement exists
     *
     * @return true when the webElement exists
     */
    public static boolean exists(WebElement element, int timer) {

        try {

            WebDriverWait wait = new WebDriverWait(SeleniumDriver.getInstance().getDriver(), timer);

            wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));

            return true;
        } catch (StaleElementReferenceException | TimeoutException | NoSuchElementException exc) {
            return false;
        }
    }


    /**
     * check if a webElement is invisible
     *
     * @param element
     * @param timer
     * @return
     */
    public static boolean elementIsInvisible(WebElement element, int timer) {
        try {

            WebDriverWait wait = new WebDriverWait(SeleniumDriver.getInstance().getDriver(), timer);

            wait.until(ExpectedConditions.refreshed(ExpectedConditions.invisibilityOf(element)));

            return true;
        } catch (StaleElementReferenceException | TimeoutException | NoSuchElementException exc) {
            return false;
        }
    }


    /**
     * mouse over the specified element
     */
    public static void hover(WebElement element) {
        Actions action = new Actions(SeleniumDriver.getInstance().getDriver());
        action.moveToElement(element).perform();
    }

    private BrowserUtils() {
        throw new IllegalStateException(CONSTRUCTION_FORBIDDEN);
    }
}

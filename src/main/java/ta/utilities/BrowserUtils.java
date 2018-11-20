package ta.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ta.driver.SeleniumDriver;

/*
 * Browser utilities class
 */
public class BrowserUtils {

    private static final String CONSTRUCTION_FORBIDDEN = "BrowserUtils - Object construction is forbidden";

    /**
     * Get the rendered page
     *
     * @return String
     */
    public static String getRenderedPage() {
        return JavascriptUtils.execute("return document.getElementsByTagName('html')[0].innerHTML").toString();
    }


    /**
     * Wait up (clickable) before throwing exception (static locator)
     *
     * @param element
     * @param timer
     */
    public static void waitForClickable(WebElement element, int timer) {
        WebDriver driver = SeleniumDriver.getInstance().getDriver();

        WebDriverWait wait = new WebDriverWait(driver, timer);

        wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(element)));
    }


    /**
     * Wait up (clickable) before throwing exception (dynamic locator)
     *
     * @param by
     * @param timer
     */
    public static void waitForClickable(By by, int timer) {
        WebDriver driver = SeleniumDriver.getInstance().getDriver();

        // wait for the dynamic element to be clickable
        WebDriverWait wait = new WebDriverWait(driver, timer);

        wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(by)));
    }


    /**
     * Wait up before throwing exception (static locator)
     *
     * @param element
     * @param timer
     */
    public static void waitFor(WebElement element, int timer) {
        WebDriver driver = SeleniumDriver.getInstance().getDriver();

        WebDriverWait wait = new WebDriverWait(driver, timer);

        wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));
    }


    /**
     * Wait up before throwing exception (dynamic locator)
     *
     * @param by
     * @param timer
     */
    public static void waitFor(By by, int timer) {
        WebDriver driver = SeleniumDriver.getInstance().getDriver();

        // wait for the dynamic element to be visible
        WebDriverWait wait = new WebDriverWait(driver, timer);

        wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(by)));
    }


    /**
     * Poll page with specified title
     *
     * @param title
     * @param timer
     */
    public static void waitForTitle(String title, int timer) {

        WebDriverWait wait = new WebDriverWait(SeleniumDriver.getInstance().getDriver(), timer);
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.titleContains(title)));
    }


    /**
     * Poll page that contains the specified URL
     *
     * @param url
     * @param timer
     */
    public static void waitForURLContains(String url, int timer) {

        WebDriver driver = SeleniumDriver.getInstance().getDriver();
        WebDriverWait wait = new WebDriverWait(driver, timer);

        wait.until(ExpectedConditions.refreshed(ExpectedConditions.urlContains(url)));
    }


    /**
     * Poll page that matches the specified URL
     *
     * @param url
     * @param timer
     */
    public static void waitForURLMatches(String url, int timer) {

        WebDriver driver = SeleniumDriver.getInstance().getDriver();
        WebDriverWait wait = new WebDriverWait(driver, timer);

        wait.until(ExpectedConditions.refreshed(ExpectedConditions.urlMatches(url)));
    }


    /**
     * Check if element exists
     *
     * @param element
     * @param timer
     * @return
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
     * Check if element is invisible
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
     * Check if element contains text
     *
     * @param element
     * @param timer
     * @param text
     * @return
     */
    public static boolean elementContainsText(WebElement element, int timer, String text) {
        try {

            WebDriverWait wait = new WebDriverWait(SeleniumDriver.getInstance().getDriver(), timer);

            wait.until(ExpectedConditions.refreshed(ExpectedConditions.textToBePresentInElement(element, text)));

            return true;
        } catch (StaleElementReferenceException | TimeoutException | NoSuchElementException exc) {
            return false;
        }
    }


    /**
     * Mouse over specified element
     *
     * @param element
     */
    public static void hover(WebElement element) {
        Actions action = new Actions(SeleniumDriver.getInstance().getDriver());
        action.moveToElement(element).perform();
    }


    /**
     * Scroll to element
     *
     * @param element
     */
    public static void scrollToElement(WebElement element) {
        int y = element.getLocation().getY();
        // to avoid element not clickable
        y = y-100;
        JavascriptUtils.execute(String.format("window.scrollTo(0, %s)", String.valueOf(y)));
    }


    private BrowserUtils() {
        throw new IllegalStateException(CONSTRUCTION_FORBIDDEN);
    }
}

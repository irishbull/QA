package ta.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ta.driver.SeleniumDriver;

/*
 * Browser utilities class
 */
public class BrowserUtils {

  /**
   * wait for page fully loaded
   * 
   * @param timeout
   * @throws Exception
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
   * 
   * @param element
   * @param timer
   * @throws Exception
   */
  public static void waitFor(WebElement element, int timer) throws Exception {
    WebDriver driver = SeleniumDriver.getInstance().getDriver();

    // wait for the static element to appear
    WebDriverWait wait = new WebDriverWait(driver, timer);

    wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));
  }


  /**
   * wait up before throwing exception (dynamic locator)
   * 
   * @param by
   * @param timer
   * @throws Exception
   */
  public static void waitFor(By by, int timer) throws Exception {
    WebDriver driver = SeleniumDriver.getInstance().getDriver();

    // wait for the dynamic element to appear
    WebDriverWait wait = new WebDriverWait(driver, timer);

    wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(by)));
  }


  /**
   * waitFor method to poll page title
   *
   * @param title
   * @param timer
   * @throws Exception
   */
  public static void waitForTitle(String title, int timer) throws Exception {

    WebDriverWait wait = new WebDriverWait(SeleniumDriver.getInstance().getDriver(), timer);
    wait.until(ExpectedConditions.refreshed(ExpectedConditions.titleContains(title)));
  }


  /**
   * waitForURL method to poll page URL
   *
   * @param url
   * @param timer
   * @throws Exception
   */
  public static void waitForURL(String url, int timer) throws Exception {

    WebDriver driver = SeleniumDriver.getInstance().getDriver();
    WebDriverWait wait = new WebDriverWait(driver, timer);

    wait.until(ExpectedConditions.refreshed(ExpectedConditions.urlContains(url)));
  }


  /**
   * modalClose method to poll page title
   *
   * @param by
   * @throws Exception
   */
  public static void modalClose(By by) throws Exception {
    WebDriver driver = SeleniumDriver.getInstance().getDriver();
    JavascriptExecutor js = (JavascriptExecutor) driver;
    WebElement elementToClick = driver.findElement(by);
    js.executeScript("arguments[0].click();", elementToClick);
  }
}

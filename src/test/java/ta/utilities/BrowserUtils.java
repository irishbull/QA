package ta.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ta.driver.SeleniumDriver;

/*
 * Browser utilities class
 */
public class BrowserUtils {
  /**
   * waitFor method to poll page title
   *
   * @param title
   * @param timer
   * @throws Exception
   */

  public static void waitFor(String title, int timer) throws Exception {

    WebDriverWait exists = new WebDriverWait(SeleniumDriver.getInstance().getDriver(), timer);
    exists.until(ExpectedConditions.refreshed(ExpectedConditions.titleContains(title)));
  }
}

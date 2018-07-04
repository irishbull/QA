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

  /*
   * isPageReady - method to check if a page is completely rendered
   * 
   * @param driver
   * 
   * @return boolean
   */
  public static boolean isPageReady() {
    JavascriptExecutor js = (JavascriptExecutor) SeleniumDriver.getInstance().getDriver();
    return (boolean) js.executeScript("return document.readyState").equals("complete");
  }


  /*
   * execute a non parameterized js command
   * 
   * @param js command to execute
   */
  public static void execute(String command) {

    JavascriptExecutor js = (JavascriptExecutor) SeleniumDriver.getInstance().getDriver();
    js.executeScript(command);
  }


  /*
   * execute a parameterized js command on WebElement
   *
   * @param command
   * 
   * @param element
   */
  public static void execute(String command, WebElement element) {
    JavascriptExecutor js = (JavascriptExecutor) SeleniumDriver.getInstance().getDriver();
    js.executeScript(command, element);
  }


  /**
   * method to execute a js click event
   * 
   * @param element
   */
  public static void click(WebElement element) {
    JavascriptExecutor js = (JavascriptExecutor) SeleniumDriver.getInstance().getDriver();
    js.executeScript("arguments[0].click();", element);
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


  private JavascriptUtils() {
    throw new IllegalStateException("JavascriptUtils - Object construction is forbidden");
  }
}

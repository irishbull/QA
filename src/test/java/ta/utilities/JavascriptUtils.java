package ta.utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import ta.driver.SeleniumDriver;

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
   * @param element
   */
  public static void click(WebElement element){
    JavascriptExecutor js = (JavascriptExecutor)SeleniumDriver.getInstance().getDriver();
    js.executeScript("arguments[0].click();", element );
  }
}

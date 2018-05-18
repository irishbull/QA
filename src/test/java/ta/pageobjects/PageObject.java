package ta.pageobjects;

import org.openqa.selenium.support.PageFactory;

import ta.driver.SeleniumDriver;

public class PageObject {

  public PageObject() {
    PageFactory.initElements(SeleniumDriver.getInstance().getDriver(), this);
  }
}

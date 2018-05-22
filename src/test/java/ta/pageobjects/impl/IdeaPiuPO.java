package ta.pageobjects.impl;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import ta.driver.SeleniumDriver;
import ta.pageobjects.PageObject;

public class IdeaPiuPO extends PageObject {

  @FindBy(how = How.TAG_NAME, using = "h1")
  WebElement h1;

  public String getTitle() {
    return SeleniumDriver.getInstance().getDriver().getTitle();
  }

  public String getH1() {
    return h1.getText();
  }

  public IdeaPiuPO() {
    super();
  }
}

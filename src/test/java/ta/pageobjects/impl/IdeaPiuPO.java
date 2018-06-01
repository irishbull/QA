package ta.pageobjects.impl;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import ta.driver.SeleniumDriver;
import ta.pageobjects.PageObject;
import ta.utilities.BrowserUtils;


public class IdeaPiuPO extends PageObject {

  @FindBy(how = How.ID, using = "idea-piu")
  WebElement div;

  public String getTitle() throws Exception {
    BrowserUtils.waitForTitle("Diventa Titolare Idea", 10);
    return SeleniumDriver.getInstance().getDriver().getTitle();
  }

  public String getUrl() throws Exception {
    BrowserUtils.waitForURL("idea-piu", 10);
    return SeleniumDriver.getInstance().getDriver().getCurrentUrl();
  }

  public String getDivAttribute() throws Exception {
    BrowserUtils.waitForPageFullyLoaded(10);
    return div.getAttribute("class");
  }

  public IdeaPiuPO() {
    super();
  }
}

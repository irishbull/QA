package ta.pageobjects.impl;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import ta.pageobjects.PageObject;

public class HomePagePO extends PageObject {

  @FindBy(how = How.NAME, using = "SELENIUM_IDEAPIU_LINK")
  private WebElement ideaPiuLink;

  @FindBy(how = How.NAME, using = "SELENIUM_COMMUNITY_LINK")
  private WebElement communityLink;

  @FindBy(how = How.NAME, using = "SELENIUM_CORSI_LINK")
  private WebElement corsiLink;

  public HomePagePO() {
    super();
  }

  public IdeaPiuPO clickIdeaPiuLink() {
    ideaPiuLink.click();
    return new IdeaPiuPO();
  }

  public WebElement getCommunityLink() {
    return communityLink;
  }

  public WebElement getCorsiLink() {
    return corsiLink;
  }
}

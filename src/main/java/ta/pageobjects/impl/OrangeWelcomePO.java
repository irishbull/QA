package ta.pageobjects.impl;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import ta.pageobjects.PageObject;

public class OrangeWelcomePO extends PageObject {

  @FindBy(how = How.ID, using = "menu_dashboard_index")
  @CacheLookup
  private WebElement menuDashboardIndex;

  @FindBy(how = How.TAG_NAME, using = "title")
  @CacheLookup
  private WebElement title;

  public WebElement getDashboardElem() {
    return menuDashboardIndex;
  }

  public String getTitle() {
    return title.getText();
  }

  public OrangeWelcomePO() {
    super();
  }
}

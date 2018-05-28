package ta.pageobjects.impl;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import ta.pageobjects.PageObject;

public class OrangeWelcomePO extends PageObject {

  @FindBy(how = How.ID, using = "menu_dashboard_index")
  private WebElement menu_dashboard_index;

  @FindBy(how = How.TAG_NAME, using = "title")
  private WebElement title;

  public WebElement getDashboardElem() {
    return menu_dashboard_index;
  }

  public String getTitle() {
    return title.getText();
  }

  public OrangeWelcomePO() {
    super();
  }
}
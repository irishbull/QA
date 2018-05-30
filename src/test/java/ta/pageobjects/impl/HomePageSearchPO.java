package ta.pageobjects.impl;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import ta.pageobjects.PageObject;

public class HomePageSearchPO extends PageObject {

  @FindBy(how = How.NAME, using = "SELENIUM_SEARCH_TOP")
  private WebElement searchWrapper;


  public HomePageSearchPO() {
    super();
  }

  public HomePageSearchResultsPO clickOnSearch() {
    searchWrapper.click();
    return new HomePageSearchResultsPO();
  }

  public WebElement getSearchWrapper() {
    return searchWrapper;
  }

}

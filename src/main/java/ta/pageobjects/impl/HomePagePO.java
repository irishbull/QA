package ta.pageobjects.impl;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import ta.pageobjects.PageObject;

public class HomePagePO extends PageObject {

  @FindBy(how = How.NAME, using = "SELENIUM_IDEAPIU_LINK")
  @CacheLookup
  private WebElement ideaPiuLink;

  @FindBy(how = How.NAME, using = "SELENIUM_COMMUNITY_LINK")
  @CacheLookup
  private WebElement communityLink;

  @FindBy(how = How.NAME, using = "SELENIUM_CORSI_LINK")
  @CacheLookup
  private WebElement corsiLink;

  @FindBy(how = How.NAME, using = "SELENIUM_PRODUCTS_MENU_DESKTOP")
  @CacheLookup
  private WebElement productMenu;

  @FindBy(how = How.NAME, using = "SELENIUM_PRODUCTS_MENU_MACROCATEGORYGROUP_WRAPPER")
  private WebElement macroCategoryGroup;

  @FindBy(how = How.NAME, using = "SELENIUM_PRODUCTS_MENU_MACROCATEGORY_WRAPPER")
  private WebElement macroCategory;

  @FindBy(how = How.NAME, using = "SELENIUM_PRODUCTS_MENU_CATEGORY_WRAPPER")
  private WebElement category;

  @FindBy(how = How.LINK_TEXT, using = "Bagno")
  private WebElement bagnoLink;

  @FindBy(how = How.XPATH, xpath = "//div[@name='SELENIUM_PRODUCTS_MENU_MACROCATEGORY_WRAPPER']/div/div/span[contains(text(), 'Docce')]")
  private WebElement docceSpan;

  @FindBy(how = How.XPATH, xpath = "//div[@name='SELENIUM_PRODUCTS_MENU_CATEGORY_WRAPPER']/div/a/div/span[contains(text(), 'Sauneee')]")
  private WebElement sauneSpan;

  @FindBy(how = How.NAME, using = "SELENIUM_SEARCH_TOP")
  @CacheLookup
  private WebElement searchWrapper;


  public IdeaPiuPO clickIdeaPiuLink() {
    ideaPiuLink.click();
    return new IdeaPiuPO();
  }

  public HomePageSearchResultsPO clickOnSearch() {
    searchWrapper.click();
    return new HomePageSearchResultsPO();
  }

  // getter
  public WebElement getIdeaPiuLink() {
    return ideaPiuLink;
  }

  public WebElement getCommunityLink() {
    return communityLink;
  }

  public WebElement getCorsiLink() {
    return corsiLink;
  }

  public WebElement getProductMenu() {
    return productMenu;
  }

  public WebElement getMacroCategoryGroup() {
    return macroCategoryGroup;
  }

  public WebElement getMacroCategory() {
    return macroCategory;
  }

  public WebElement getCategory() {
    return category;
  }

  public WebElement getBagnoLink() {
    return bagnoLink;
  }

  public WebElement getDocceSpan() {
    return docceSpan;
  }

  public WebElement getSauneSpan() {
    return sauneSpan;
  }

}
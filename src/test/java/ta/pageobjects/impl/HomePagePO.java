package ta.pageobjects.impl;

import org.openqa.selenium.By;
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

  @FindBy(how = How.NAME, using = "SELENIUM_PRODUCTS_MENU_DESKTOP")
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

  @FindBy(how = How.XPATH, xpath = "//div[@name='SELENIUM_PRODUCTS_MENU_CATEGORY_WRAPPER']/div/a/div/span[contains(text(), 'Saune')]")
  private WebElement sauneSpan;


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

  public IdeaPiuPO clickIdeaPiuLink() {
    WebElement parent = ideaPiuLink.findElement(By.xpath(".."));
    parent.click();
    return new IdeaPiuPO();
  }

}

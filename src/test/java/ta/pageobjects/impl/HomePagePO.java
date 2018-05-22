package ta.pageobjects.impl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import ta.pageobjects.PageObject;

public class HomePagePO extends PageObject {

  @FindBy(how = How.NAME, using = "SELENIUM_IDEAPIU_MENU")
  private WebElement span;

  public HomePagePO() {
    super();
  }

  public String getSpanText() {
    return span.getText();
  }

  public IdeaPiuPO clickLink() {
    WebElement parent = span.findElement(By.xpath(".."));
    parent.click();
    return new IdeaPiuPO();
  }
}

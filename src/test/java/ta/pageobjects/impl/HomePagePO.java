package ta.pageobjects.impl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.nio.charset.StandardCharsets;

import ta.pageobjects.PageObject;

public class HomePagePO extends PageObject {

  @FindBy(how = How.NAME, using = "SELENIUM_IDEAPIU_MENU")
  private WebElement span;

  public HomePagePO() {
    super();
  }

  public String getSpanText() {
    // return a UTF-16 string from an array of bytes that is supposed to be in UTF-8
    return new String(span.getText().getBytes(StandardCharsets.UTF_8));
  }

  public IdeaPiuPO clickLink() {
    WebElement parent = span.findElement(By.xpath(".."));
    parent.click();
    return new IdeaPiuPO();
  }
}

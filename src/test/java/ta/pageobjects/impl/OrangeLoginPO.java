package ta.pageobjects.impl;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ta.pageobjects.PageObject;


public class OrangeLoginPO extends PageObject {

  @FindBy(how = How.ID, using = "txtUsername")
  @CacheLookup
  private WebElement username;

  @FindBy(how = How.ID, using = "txtPassword")
  @CacheLookup
  private WebElement password;

  @FindBy(how = How.ID, using = "btnLogin")
  @CacheLookup
  private WebElement submitButton;

  public OrangeLoginPO() {
    super();
  }

  public void enterUsernameAndPassword(String username, String password) throws Exception {
    this.username.clear();
    this.username.sendKeys(username);
    this.password.clear();
    this.password.sendKeys(password);
  }

  public OrangeWelcomePO submit() {
    this.submitButton.submit();
    return new OrangeWelcomePO();
  }
}

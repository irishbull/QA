package ta.pageobjects.impl;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import ta.pageobjects.PageObject;

public class SignUpPO extends PageObject {

  @FindBy(id = "firstname")
  private WebElement firstName;

  @FindBy(id = "lastname")
  private WebElement lastName;

  @FindBy(id = "address")
  private WebElement address;

  @FindBy(id = "zipcode")
  private WebElement zipCode;

  @FindBy(id = "signup")
  private WebElement submitButton;

  public SignUpPO() {
    super();
  }

  public boolean isInitialized() {
    return firstName.isDisplayed();
  }

  public void enterName(String firstName, String lastName) {
    this.firstName.clear();
    this.firstName.sendKeys(firstName);

    this.lastName.clear();
    this.lastName.sendKeys(lastName);
  }

  public void enterAddress(String address, String zipCode) {
    this.address.clear();
    this.address.sendKeys(address);

    this.zipCode.clear();
    this.zipCode.sendKeys(zipCode);
  }

  public ReceiptPO submit() {
    submitButton.click();
    return new ReceiptPO();
  }
}
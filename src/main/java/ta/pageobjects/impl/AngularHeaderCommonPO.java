package ta.pageobjects.impl;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import ta.pageobjects.PageObject;

public class AngularHeaderCommonPO extends PageObject {

    @FindBy(how = How.ID, using = "email")
    @CacheLookup
    private WebElement email;

    @FindBy(how = How.ID, using = "password")
    @CacheLookup
    private WebElement password;

    @FindBy(how = How.NAME, using = "loginForm")
    @CacheLookup
    private WebElement loginForm;

    @FindBy(how = How.CLASS_NAME, using = "error-login")
    private WebElement loginErrorMessageWrapper;
}

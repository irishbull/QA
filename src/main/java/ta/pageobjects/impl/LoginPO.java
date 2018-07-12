package ta.pageobjects.impl;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import ta.pageobjects.PageObject;

public class LoginPO extends PageObject {

    @FindBy(how = How.ID, using = "email")
    @CacheLookup
    private WebElement email;

    @FindBy(how = How.ID, using = "password")
    @CacheLookup
    private WebElement password;

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/main/div/div/div/div/div/div/div/div[1]/form/div[2]/div/button")
    @CacheLookup
    private WebElement loginButton;

    public void enterUsernameAndPassword(String email, String password) throws Exception {
        this.email.clear();
        this.email.sendKeys(email);
        this.password.clear();
        this.password.sendKeys(password);
    }

    public HomePagePO clickLoginButton() {
        this.loginButton.submit();
        return new HomePagePO();
    }
}

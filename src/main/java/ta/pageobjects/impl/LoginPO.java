package ta.pageobjects.impl;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import ta.pageobjects.PageObject;

public class LoginPO extends PageObject {

    @FindBy(how = How.ID_OR_NAME, using = "_ACCEDI")
    private WebElement accedi;

    @FindBy(how = How.ID_OR_NAME, using = "consent_prompt_submit")
    private WebElement accettacookie;

    @FindBy(how = How.ID_OR_NAME, using = "mylm_logout")
    private WebElement logout;

    @FindBy(how = How.ID_OR_NAME, using = "email")
    @CacheLookup
    private WebElement email;

    @FindBy(how = How.ID_OR_NAME, using = "password")
    @CacheLookup
    private WebElement password;

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/main/div/div/div/div/div/div/div/div[1]/form/div[2]/div/button")
    @CacheLookup
    private WebElement loginButton;

    @FindBy(how = How.CLASS_NAME, using = "error-login")
    private WebElement loginErrorMessageWrapper;

    public void enterUsernameAndPassword(String email, String password) {
        this.email.clear();
        this.email.sendKeys(email);
        this.password.clear();
        this.password.sendKeys(password);
    }

    public HomePagePO clickLoginButton() {
        this.loginButton.submit();
        return new HomePagePO();
    }

    public WebElement getLoginErrorMessageWrapper() {
        return this.loginErrorMessageWrapper;
    }

    public void clickAccedi(){ accedi.click(); }

    public void clickcookie(){ accettacookie.click(); }

    public void logout(){ logout.click(); }


}

package ta.pageobjects.impl;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import ta.pageobjects.PageObject;

public class AngularMyLmPO extends PageObject {

    @FindBy(how = How.CLASS_NAME, using = "mylm-home-container")
    private WebElement myLmContainer;

    @FindBy(how = How.NAME, using = "mylm_logout")
    private WebElement logout;

    public void logout() {
        logout.click();
    }
}

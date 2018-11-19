package ta.pageobjects.impl;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import ta.pageobjects.PageObject;

public class AngularHeaderCommonPO extends PageObject {

    @FindBy(how = How.CLASS_NAME, using = "common-voice-container")
    private WebElement commonVoicesContainer;

    @FindBy(how = How.CLASS_NAME, using = "customer-info-name")
    private WebElement customerName;

    @FindBy(how = How.CLASS_NAME, using = "logo-container")
    private WebElement logoContainer;

    public WebElement getCustomerName() {
        return customerName;
    }

    public WebElement getLogoContainer() {
        return logoContainer;
    }

    public void clickLinkToHome() {
        logoContainer.click();
    }
}

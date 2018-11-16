package ta.pageobjects.impl;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import ta.pageobjects.PageObject;

public class AngularHeaderCommonPO extends PageObject {

    @FindBy(how = How.CLASS_NAME, using = "common-voice-container")
    @CacheLookup
    private WebElement commonVoicesContainer;

    @FindBy(how = How.CLASS_NAME, using = "customer-info-name")
    private WebElement customerName;

    public WebElement getCustomerName() {
        return customerName;
    }
}

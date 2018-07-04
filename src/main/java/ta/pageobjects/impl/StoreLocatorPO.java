package ta.pageobjects.impl;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import ta.pageobjects.PageObject;

public class StoreLocatorPO extends PageObject {

    @FindBy(how = How.NAME, using = "SELENIUM_STORE_LOCATOR_DESKTOP")
    @CacheLookup
    private WebElement storeLocatorDiv;

    @FindBy(how = How.NAME, using = "SELENIUM_STORE_LOCATOR_POPUP")
    private WebElement storeLocatorPopup;

    @FindBy(how = How.XPATH, xpath = "//div[@name='SELENIUM_STORE_LOCATOR_POPUP']/div/div/div/div/a")
    private WebElement seeOnMapLink;


    // getter
    public WebElement getStoreLocatorDiv() {
        return storeLocatorDiv;
    }

    public WebElement getStoreLocatorPopup() {
        return storeLocatorPopup;
    }

    public WebElement getSeeOnMapLink() {
        return seeOnMapLink;
    }
}

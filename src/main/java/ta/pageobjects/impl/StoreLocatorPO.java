package ta.pageobjects.impl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import ta.pageobjects.PageObject;
import ta.utilities.BrowserUtils;
import ta.utilities.constants.Constants;

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

    public boolean isStoreLocationValid(String expectedValue) {
        //TODO webElement SELENIUM_STORE_LOCATOR_DESKTOP/div/div/div/div/div[@class='jss63']/span
        WebElement storeLocation = storeLocatorDiv.findElement(By.xpath("./div/div/div/div/div[@class='jss63']/span"));
        return BrowserUtils.elementContainsText(storeLocation, Constants.WaitTime.EXPLICIT_WAIT, expectedValue);
    }

}

package ta.pageobjects.impl;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import ta.pageobjects.PageObject;
import ta.utilities.BrowserUtils;
import ta.utilities.constants.Constants;

public class CartPO extends PageObject {

    @FindBy(how = How.CLASS_NAME, using = "remove-item")
    private WebElement removeItem;

    public void removeItemFromCart() {
        BrowserUtils.waitFor(removeItem, Constants.WaitTime.EXPLICIT_WAIT);
        removeItem.click();
    }
}

package ta.pageobjects.impl;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import ta.pageobjects.PageObject;
import ta.utilities.BrowserUtils;
import ta.utilities.constants.Constants;

public class AddToCartOverlayPO extends PageObject {

    @FindBy(how = How.NAME, using = "ADD_TO_CART_OVERLAY_CONTINUE_SHOPPING_BUTTON")
    private WebElement buttonContinue;

    @FindBy(how = How.NAME, using = "ADD_TO_CART_OVERLAY_ADD_TO_CART_BUTTON")
    private WebElement buttonNavToCart;

    @FindBy(how = How.NAME, using = "PRODUCT_QUANTITY")
    private WebElement quantity;

    @FindBy(how = How.NAME, using = "ADD_TO_CART_LABEL")
    private WebElement cartLabel;

    public String getProductQuantity() {
        return quantity.getText();
    }

    public String getCartLabelText() {
        return cartLabel.getText();
    }

    public void continueShopping() {
        BrowserUtils.waitFor(buttonContinue, Constants.WaitTime.EXPLICIT_WAIT);
        buttonContinue.click();
    }

    public void goToCart() {
        BrowserUtils.waitFor(buttonNavToCart, Constants.WaitTime.EXPLICIT_WAIT);
        buttonNavToCart.click();
    }
}

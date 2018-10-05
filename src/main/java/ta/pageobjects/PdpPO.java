package ta.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import ta.pageobjects.impl.AddToCartOverlayPO;
import ta.pageobjects.impl.SelectStoreModalPO;
import ta.utilities.BrowserUtils;
import ta.utilities.constants.Constants;

public class PdpPO extends PageObject {

    @FindBy(how = How.NAME, using = "CATALOG_PAGE_CHANGE_STORE")
    private WebElement changeStore;

    @FindBy(how = How.NAME, using = "CATALOG_PAGE_SELECT_STORE")
    private WebElement selectStore;

    //TODO name without article code
    @FindBy(how = How.NAME, using = "ARTICLE_35881384_ADD_TO_CART_BUTTON")
    private WebElement addToCartButton;

    public SelectStoreModalPO openSelectStoreModal() {
        BrowserUtils.waitForClickable(changeStore, Constants.WaitTime.EXPLICIT_WAIT);
        changeStore.click();
        return new SelectStoreModalPO();
    }

    public AddToCartOverlayPO addToCart() {
        BrowserUtils.waitForClickable(addToCartButton, Constants.WaitTime.EXPLICIT_WAIT);
        addToCartButton.click();
        return new AddToCartOverlayPO();
    }
}

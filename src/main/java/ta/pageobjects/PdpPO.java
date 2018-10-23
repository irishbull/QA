package ta.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import ta.pageobjects.impl.AddToCartOverlayPO;
import ta.pageobjects.impl.SelectStoreModalPO;
import ta.utilities.BrowserUtils;
import ta.utilities.constants.Constants;

public class PdpPO extends PageObject {

    /* TODO waiting fix
    @FindBy(how = How.NAME, using = "CATALOG_PAGE_CHANGE_STORE")
    private WebElement changeStore;
    */
    // TODO remove
    @FindBy(how = How.XPATH, using = "//span[text()='Cambia negozio']")
    private WebElement changeStore;

    @FindBy(how = How.NAME, using ="CATALOG_PAGE_CURRENT_STORE")
    private WebElement currentStore;

    @FindBy(how = How.NAME, using = "CATALOG_PAGE_SELECT_STORE")
    private WebElement selectStore;

    @FindBy(how = How.NAME, using = "ARTICLE_ADD_TO_CART_BUTTON")
    private WebElement addToCartButton;

    @FindBy(how = How.NAME, using = "COUNTER_INCREMENT")
    private WebElement incrementQuantityButton;

    @FindBy(how = How.NAME, using = "COUNTER_DECREMENT")
    private WebElement decrementQuantityButton;


    public SelectStoreModalPO openSelectStoreModal() {
        BrowserUtils.scrollToElement(changeStore);

        //TODO use change store (CATALOG_PAGE_CHANGE_STORE)

        //BrowserUtils.waitForClickable(changeStore, Constants.WaitTime.EXPLICIT_WAIT);
        changeStore.click();
        return new SelectStoreModalPO();
    }

    public AddToCartOverlayPO addToCart() {
        BrowserUtils.waitForClickable(addToCartButton, Constants.WaitTime.EXPLICIT_WAIT);
        addToCartButton.click();
        return new AddToCartOverlayPO();
    }

    public void incrementQuantity(int qt) {
        for (int i = 0; i < qt; i++) {
            BrowserUtils.waitForClickable(incrementQuantityButton, Constants.WaitTime.EXPLICIT_WAIT);
            incrementQuantityButton.click();
        }
    }

    public void decrementQuantity(int qt) {
        for (int i = 0; i < qt; i++) {
            BrowserUtils.waitForClickable(decrementQuantityButton, Constants.WaitTime.EXPLICIT_WAIT);
            decrementQuantityButton.click();
        }
    }
}

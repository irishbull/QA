package ta.pageobjects.impl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import ta.pageobjects.PageObject;
import ta.utilities.BrowserUtils;
import ta.utilities.constants.Constants;

public class SelectStoreModalPO extends PageObject {

    @FindBy(how = How.NAME, using = "MODAL_STORE_STOCK")
    private WebElement modal;

    @FindBy(how = How.ID_OR_NAME, using = "storeSelect")
    private WebElement selectStore;

    @FindBy(how = How.NAME, using = "SELECT_OPTIONS_CONTAINER")
    private WebElement optionsContainer;

    public StoreLocatorPO selectStore(String storeId) {

        BrowserUtils.waitForClickable(selectStore, Constants.WaitTime.EXPLICIT_WAIT);
        selectStore.click();

        String xpath = String.format("./div[(@data-name='storeSelect') and (@data-value='%s')]", storeId);

        WebElement selectedStore = optionsContainer.findElement(By.xpath(xpath));

        BrowserUtils.scrollToElement(selectedStore);
        BrowserUtils.waitForClickable(selectedStore, Constants.WaitTime.EXPLICIT_WAIT);
        selectedStore.click();

        return new StoreLocatorPO();
    }
}

package ta.pageobjects.impl;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import ta.pageobjects.PageObject;
import ta.utilities.BrowserUtils;
import ta.utilities.constants.Constants;

public class ChangeStoreModalPO extends PageObject {

    @FindBy(how = How.ID_OR_NAME, using = "storeSelect")
    private WebElement selectStore;

    @FindBy(how = How.CSS, using = "div[data-name='storeSelect']")
    private WebElement store;

    @FindBy(how = How.XPATH, using = "//div[(@data-name='storeSelect') and (@data-value='6')]")
    private WebElement store6;

    public StoreLocatorPO changeStore() {

        BrowserUtils.waitForClickable(selectStore, Constants.WaitTime.EXPLICIT_WAIT);
        selectStore.click();


        //TODO store = storeContainer.find(data.value=dynamic)

        BrowserUtils.scrollToElement(store6);
        BrowserUtils.waitForClickable(store6, Constants.WaitTime.EXPLICIT_WAIT);
        store6.click();

        return new StoreLocatorPO();
    }
}

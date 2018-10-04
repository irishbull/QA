package ta.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import ta.pageobjects.impl.SelectStoreModalPO;

public class PdpPO extends PageObject {

    @FindBy(how = How.NAME, using = "CATALOG_PAGE_CHANGE_STORE")
    private WebElement changeStore;

    @FindBy(how = How.NAME, using = "CATALOG_PAGE_SELECT_STORE")
    private WebElement selectStore;

    public SelectStoreModalPO openSelectStoreModal() {
        changeStore.click();
        return new SelectStoreModalPO();
    }
}

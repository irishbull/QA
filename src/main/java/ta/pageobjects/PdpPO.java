package ta.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import ta.pageobjects.impl.ChangeStoreModalPO;

public class PdpPO extends PageObject {

    @FindBy(how = How.XPATH, xpath = "//span[contains(text(), 'Cambia negozio')]")
    private WebElement changeStoreDiv;

    public ChangeStoreModalPO openChangeStoreModal() {
        changeStoreDiv.click();
        return new ChangeStoreModalPO();
    }
}

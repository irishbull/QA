package ta.pageobjects.impl.example;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import ta.pageobjects.PageObject;

public class ReceiptPO extends PageObject {

    @FindBy(tagName = "h1")
    private WebElement header;

    public ReceiptPO() {
        super();
    }

    public boolean isInitialized() {
        return header.isDisplayed();
    }

    public String confirmationHeader() {
        return header.getText();
    }

}

package ta.pageobjects.impl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import ta.pageobjects.PageObject;

public class HeaderPO extends PageObject {
/*
    TODO

    @FindBy(how = How.NAME, using = "SELENIUM_HEADER_MENU_LOGIN_TEXT")

    @FindBy(how = How.NAME, using = "SELENIUM_HEADER_MENU_LOGIN_LINK")

*/

    @FindBy(how = How.NAME, using = "SELENIUM_HEADER_MENU_LOGIN_ICON")
    private WebElement headerLoginElem;

    @FindBy(how = How.NAME, using = "QUANTITY_COUNTER")
    private WebElement cartQuantityCounter;

    public String getCustomerName() {
        return headerLoginElem.findElement(By.tagName("div")).getText();
    }

    public String getCartQuantity() {
        return cartQuantityCounter.getText();
    }
}

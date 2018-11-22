package ta.pageobjects.impl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import ta.pageobjects.PageObject;

public class AngularCartItemsListPO extends PageObject {

    @FindBy(how = How.CLASS_NAME, using = "cart-items-list")
    private WebElement cartItemsList;

    public String getListSize() {
        WebElement counterWrapper = cartItemsList.findElements(By.tagName("div")).get(0);
        return counterWrapper.getAttribute("data-total-items");
    }

}

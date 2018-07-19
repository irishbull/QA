package ta.pageobjects.impl;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import ta.pageobjects.PageObject;

public class CategoryPO extends PageObject {

    @FindBy(how = How.NAME, using = "productsNavBar")
    @CacheLookup
    private WebElement productsNavBar;

    public WebElement getProductsNavBar() {
        return productsNavBar;
    }

}
